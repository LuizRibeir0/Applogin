package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import br.appLogin.appLogin.service.autenticator.LoginInterceptor;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// veio por padrão quando criei a classe. - import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CookieService cookieService;

    @MockBean
    private LoginInterceptor interceptor;

    @BeforeEach
    void setUp() throws Exception{
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }


    @Test
    void RetornarPaginaLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void RetornarPaginagames() throws Exception {
        Cookie cookie = new Cookie("usuarioId", "1");

        when(cookieService.getCookie(any(HttpServletRequest.class), eq("usuarioId")))
                .thenReturn("1");

        when(cookieService.getCookie(any(HttpServletRequest.class), eq("nomeUsuario")))
                .thenReturn("Luiz");

        mockMvc.perform(get("/").cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(model().attribute("nome", "Luiz"))
                .andExpect(view().name("index"));


        verify(cookieService, times(1)).getCookie(any(HttpServletRequest.class), eq("nomeUsuario"));
    }

    @Test
    void loginUsuario() throws Exception {
        Usuario usuarioMockado = new Usuario();

        usuarioMockado.setId(1L);
        usuarioMockado.setNome("Luiz");
        usuarioMockado.setEmail("luiz@gmail.com");
        usuarioMockado.setSenha("123");

        when(usuarioRepository.login("luiz@gmail.com", "123")).thenReturn(usuarioMockado);


        mockMvc.perform(post("/logar")
                .param("email", "luiz@gmail.com")
                .param("senha", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(cookieService).setCookie(any(HttpServletResponse.class), eq("usuarioId"), eq("1"), eq(10000));
        verify(cookieService).setCookie(any(HttpServletResponse.class), eq("nomeUsuario"), eq("Luiz"), eq(10000));
    }

    @Test
    void erroNoLogin () throws Exception {

        when(usuarioRepository.login("luiz@gmail.com", "123")).thenReturn(null);

        mockMvc.perform(post("/logar")
                .param("email", "luiz@gmail.com")
                .param("senha", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("erro", "usuario invalido!"));

        verify(cookieService, never()).setCookie(any(), any(), any(), anyInt());
    }

    @Test
    void cadastro() throws Exception {
        mockMvc.perform(get("/cadastroUsuario"))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastro"));
    }

    @Test
    void cadastroUsuario() throws Exception {

        mockMvc.perform(post("/cadastroUsuario")
                .param("nome", "Luiz")
                .param("email", "luiz@gmail.com")
                .param("senha", "123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void erroNoCadastro () throws Exception {

        mockMvc.perform(post("/cadastroUsuario")
                .param("nome", "")
                .param("email", "emailInvalido")
                .param("senha", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cadastroUsuario"));

        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import br.appLogin.appLogin.service.autenticator.LoginInterceptor;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private HandlerInterceptor loginInterceptor;

    @MockBean
    private LoginInterceptor interceptor;

    @BeforeEach
    void setUp() throws Exception{
        when(loginInterceptor.preHandle(any(), any(), any())).thenReturn(true);
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
    void loginUsuario() {
    }

    @Test
    void cadastro() {
    }

    @Test
    void cadastroUsuario() {
    }
}
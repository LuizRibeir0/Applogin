package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findById() {

        Usuario usuarioMock = new Usuario();
        usuarioMock.setNome("Luiz");
        usuarioMock.setEmail("luiz@gmail.com");
        usuarioMock.setSenha("123");
        usuarioRepository.save(usuarioMock);

        when(usuarioRepository.login("luiz@gmail.com", "123")).thenReturn(usuarioMock);

        Usuario usuariomockado = usuarioRepository.login("luiz@gmail.com", "123");

        assertNotNull(usuariomockado);
        assertEquals("Luiz", usuarioMock.getNome());
        verify(usuarioRepository, times(1)).login("luiz@gmail.com", "123");
    }

    @Test
    void login() {
        Usuario usuario = usuarioRepository.login("invalido@gmail.com", "senha");

        assertNull(usuario);

    }
}
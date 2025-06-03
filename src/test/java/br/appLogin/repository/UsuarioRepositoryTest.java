package br.appLogin.repository;

import br.appLogin.appLogin.AppLoginApplication;
import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = AppLoginApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UsuarioRepositoryTest {

    @Autowired //injeção de dependencia
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp(){
//        Usuario u = new Usuario();  // instancia da classe Usuario
//        u.setNome("Luiz");
//        u.setEmail("luiz@gmail.com");
//        u.setSenha("123");
//
//        usuarioRepository.save(u);
    }

    @AfterEach
    public void afterEach() {
        usuarioRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void encontrarUsuarioPorEmaileSenha(){
        Usuario u = new Usuario();  // instancia da classe Usuario
        u.setNome("LuizTest");
        u.setEmail("luizTest@gmail.com");
        u.setSenha("123test");

        usuarioRepository.save(u);
        Usuario usuario = usuarioRepository.login("luizTest@gmail.com", "123test");

        assertNotNull(usuario);
        assertEquals("LuizTest", usuario.getNome());

    }

    @Test
    @Order(2)
    public void retornarNullParaUsuarioInexistente(){
        Usuario usuario = usuarioRepository.login("invalido@gmail.com", "senha");

        assertNull(usuario);
    }
}

package br.appLogin.appLogin.controller;


import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login () {
        return "login";
    }

    @GetMapping("/")
    public String dashboard () {
        return "index";
    }

    @PostMapping("/logar")
    public String loginUsuario (Usuario usuario, Model model, HttpServletResponse response) {
        Usuario user = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if (usuario != null) {
            return "redirect:/";
        }

        return "login";

    }










    @GetMapping("/cadastroUsuario")
    public String cadastro () {
        return "cadastro";
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/cadastroUsuario";
        }
        ur.save(usuario);
        return "redirect:/login";
    }

}

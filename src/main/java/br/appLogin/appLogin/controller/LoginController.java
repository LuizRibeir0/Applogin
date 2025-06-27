package br.appLogin.appLogin.controller;


import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Autowired
    private CookieService cookieService;

    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login () {
        return "login";
    }

    @GetMapping("/")
    public String games (Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", cookieService.getCookie(request, "nomeUsuario"));
        return "index";
    }

    @PostMapping("/logar")
    public String loginUsuario (Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        Usuario user = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if (user != null) {
            cookieService.setCookie(response,"usuarioId", String.valueOf(user.getId()), 10000);
            cookieService.setCookie(response,"nomeUsuario", String.valueOf(user.getNome()), 10000);
            return "redirect:/";
        }

        model.addAttribute("erro", "usuario invalido!");
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

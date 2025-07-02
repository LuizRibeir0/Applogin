package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class BuscarUsuarios {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(description = "lista todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista dos usuarios"),
    })
    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @Operation(description = "lista os usuarios pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista dos usuarios pelo Id"),
            @ApiResponse(responseCode = "400", description = "Não existe usuario com esse Id")
    })
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable long id){
        return usuarioRepository.findById(id);
    }
}

package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.constants.Descriptions;
import br.appLogin.appLogin.constants.HttpsResponseCode;
import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.model.UsuarioDTO;
import br.appLogin.appLogin.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class BuscarUsuarios {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> buscarTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    @Operation(description = "lista todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpsResponseCode.HTTP_RESPONSE_CODE_OK,
                    description = "lista dos usuarios"),
    })
    @GetMapping
    public List<UsuarioDTO> listar(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    @Operation(description = "lista os usuarios pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpsResponseCode.HTTP_RESPONSE_CODE_OK,
                    description = "lista dos usuarios pelo Id"),
            @ApiResponse(responseCode = HttpsResponseCode.HTTP_RESPONSE_CODE_NOT_FOUND,
                    description = Descriptions.DESCRIPTIONS_NOT_USER_ID)
    })

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable long id){
        return usuarioRepository.findById(id);
    }

    @Operation(description = "deleta o usuario pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpsResponseCode.HTTP_RESPONSE_CODE_OK,
                    description = "deleta um usuario"),
            @ApiResponse(responseCode = HttpsResponseCode.HTTP_RESPONSE_CODE_NOT_FOUND,
                    description = Descriptions.DESCRIPTIONS_NOT_USER_ID)
    })
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable long id){
        usuarioRepository.deleteById(String.valueOf(id));
    }
}

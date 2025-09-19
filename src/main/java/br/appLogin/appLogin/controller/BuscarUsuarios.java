package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.model.UsuarioDTO;
import br.appLogin.appLogin.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.appLogin.appLogin.constants.Descriptions.DELET_USER_BY_ID;
import static br.appLogin.appLogin.constants.Descriptions.DESCRIPTIONS_NOT_USER_ID;
import static br.appLogin.appLogin.constants.Descriptions.LIST_USER_;
import static br.appLogin.appLogin.constants.HttpsResponseCode.HTTP_RESPONSE_CODE_NOT_FOUND;
import static br.appLogin.appLogin.constants.HttpsResponseCode.HTTP_RESPONSE_CODE_OK;
import static br.appLogin.appLogin.constants.SummarySwaggerConstants.DELETE_USER_BY_ID;
import static br.appLogin.appLogin.constants.SummarySwaggerConstants.LIST_ALL_USER;
import static br.appLogin.appLogin.constants.SummarySwaggerConstants.LIST_USER_BY_ID;

@RestController
@RequestMapping("/usuarios")
public class BuscarUsuarios {

    private final UsuarioService usuarioService;

    public BuscarUsuarios(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<UsuarioDTO> buscarTodosUsuarios() {
        return usuarioService.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    @Operation(
            summary = LIST_ALL_USER
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_RESPONSE_CODE_OK,
                    description = LIST_USER_),
    })
    @GetMapping
    public List<UsuarioDTO> listar(){
        return usuarioService.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    @Operation(
            summary = LIST_USER_BY_ID
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_RESPONSE_CODE_OK,
                    description = LIST_USER_BY_ID),
            @ApiResponse(responseCode = HTTP_RESPONSE_CODE_NOT_FOUND,
                    description = DESCRIPTIONS_NOT_USER_ID)
    })

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable long id){
        return usuarioService.findById(id);
    }

    @Operation(
            summary = DELETE_USER_BY_ID
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_RESPONSE_CODE_OK,
                    description = DELET_USER_BY_ID),
            @ApiResponse(responseCode = HTTP_RESPONSE_CODE_NOT_FOUND,
                    description = DESCRIPTIONS_NOT_USER_ID)
    })
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable long id){
        usuarioService.deleteById(String.valueOf(id));
    }
}

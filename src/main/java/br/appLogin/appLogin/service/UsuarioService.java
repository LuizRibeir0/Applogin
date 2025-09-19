package br.appLogin.appLogin.service;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.model.UsuarioDTO;
import br.appLogin.appLogin.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login (String email, String senha){
            return usuarioRepository.login(email, senha);
    }

    public Usuario save(@Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List <UsuarioDTO> buscarTodosUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    public Collection<Object> findAll() {
        return null;
    }

    public Usuario findById(long id) {
        return null;
    }

    public void deleteById(String s) {

    }
}

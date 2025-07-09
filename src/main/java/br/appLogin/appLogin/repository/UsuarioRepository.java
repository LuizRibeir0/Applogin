package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,String> {

    @Override
    List<Usuario> findAll();

    Usuario findById(long id);
    Usuario deleteUsuarioById(long id);

    @Query(value = "select * from applogin.usuario where email = :email and senha = :senha", nativeQuery = true)
    public Usuario login(String email, String senha);

}

package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {


    Usuario findById(long id);

    @Query(value = "select * from applogin.usuario where email = :email and senha = :senha", nativeQuery = true)
    public Usuario login(String email, String senha);

}

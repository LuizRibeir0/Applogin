package br.appLogin.appLogin.model;

public class UsuarioDTO {

    private long Id;
    private String nome;
    private String email;

    public UsuarioDTO () {}

    public UsuarioDTO(Usuario usuario) {
       this.Id = usuario.getId();
       this.nome = usuario.getNome();
       this.email = usuario.getEmail();
    }

    public UsuarioDTO(UsuarioDTO usuarioDTO) {
    }

    public UsuarioDTO(Object o) {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.demo.DTOs;

import com.example.demo.model.Usuario;

public class UsuarioDTO {
  private Integer idUsuario;
  private String nombreCompleto;
  private String correo;
  private String telefono;
  private String usuario;
  private String pass;

  public UsuarioDTO(){}

  // Constructor
  public UsuarioDTO(Integer idUsuario,String usuario, String nombreCompleto, 
                     String correo, String telefono,String pass) {
    this.idUsuario = idUsuario;
    this.nombreCompleto = nombreCompleto;
    this.correo = correo;
    this.telefono = telefono;
    this.usuario = usuario;
    this.pass = pass;
  }

  // Getters y setters
  public String getPass(){
    return pass;
}

 public void setPass(String pass){
    this.pass = pass;
 }
  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }
  public String getUsuario(){
    return usuario;
}

public void setUsuario(String usuario){
    this.usuario = usuario;
}


  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  // Método para crear un UsuarioDTO a partir de una entidad Usuario
  public static UsuarioDTO fromUsuario(Usuario usuario) {
    if (usuario != null) {
      return new UsuarioDTO(
        usuario.getIdUsuario(),
        usuario.getNombreCompleto(),
        usuario.getCorreo(),
        usuario.getTelefono(),
        usuario.getUsuario(),
        usuario.getPass()
      );
    }
    return null;
  }

}

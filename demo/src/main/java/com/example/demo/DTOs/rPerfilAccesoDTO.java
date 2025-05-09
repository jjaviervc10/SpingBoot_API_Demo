package com.example.demo.DTOs;
import java.util.Date;

import com.example.demo.model.Usuario;
import com.example.demo.model.rPerfilAcceso;


public class rPerfilAccesoDTO {
  //private rPerfilAcceso Id;
  private Integer idPerfil;
  private Integer idAcceso;
  private UsuarioDTO usuarioDTO;
  private Integer idUsuario;  // Cambiar de UsuarioDTO a Integer
  private Date fechaServidor;

  public rPerfilAccesoDTO() {
    // Constructor vacío para Jackson
  }
  // Constructor
  public rPerfilAccesoDTO(Integer idPerfil, Integer idAcceso, Integer idUsuario, Date fechaServidor) {
    this.idPerfil = idPerfil;
    this.idAcceso = idAcceso;
    this.idUsuario = idUsuario;
    this.fechaServidor = fechaServidor;
}

  //Constructor
public rPerfilAccesoDTO ( rPerfilAcceso Id, UsuarioDTO usuarioDTO, Date fechaServidor){
  this.idPerfil = Id.getId().getIdPerfil();
  this.idAcceso = Id.getId().getIdAcceso();
  this.fechaServidor = fechaServidor;
  this.usuarioDTO = usuarioDTO;
     // Crear UsuarioDTO a partir del idUsuario de rPerfilAcceso
   //  this.usuarioDTO = new UsuarioDTO(Id.getUsuario().getIdUsuario());
    // Utilizar el método estático fromUsuario para convertir idUsuario a UsuarioDTO
  this.usuarioDTO = UsuarioDTO.fromUsuario(Id.getUsuario());  
}

 // Constructor
public rPerfilAccesoDTO(rPerfilAcceso rPerfilAcceso) {
    this.idPerfil = rPerfilAcceso.getId().getIdPerfil();
    this.idAcceso = rPerfilAcceso.getId().getIdAcceso();
    this.fechaServidor = rPerfilAcceso.getFechaServidor();
    // Utilizar el método estático fromUsuario para convertir idUsuario a UsuarioDTO
    this.usuarioDTO = UsuarioDTO.fromUsuario(rPerfilAcceso.getUsuario());
}

   // Getters y Setters
public Integer getIdUsuario() {
    return idUsuario;
}

public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
}
   public Integer getIdPerfil() {
    return idPerfil;
}

public void setIdPerfil(Integer idPerfil) {
    this.idPerfil = idPerfil;
}

public Integer getIdAcceso() {
    return idAcceso;
}

public void setIdAcceso(Integer idAcceso) {
    this.idAcceso = idAcceso;
}

public Date getFechaServidor() {
    return fechaServidor;
}

public void setFechaServidor(Date fechaServidor) {
    this.fechaServidor = fechaServidor;
}

public UsuarioDTO getUsuarioDTO(){
    return usuarioDTO;
 }

 public void setUsuarioDTO(UsuarioDTO usuarioDTO){
     this.usuarioDTO = usuarioDTO;
 }

}

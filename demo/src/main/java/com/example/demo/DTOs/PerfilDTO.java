package com.example.demo.DTOs;

import java.util.Date;

import com.example.demo.model.Perfil;
import com.example.demo.model.Usuario;

public class PerfilDTO {
  private Integer idPerfil;
  private String nombrePerfil;
  private String descripcionPerfil;
  private boolean activo;
  private Date fechaServidor;
  private Date fechaAlta;
  private UsuarioDTO usuarioDTO;

  //Constructor 
  public PerfilDTO(Integer idPerfil, String nombrePerfil, String descripcionPerfil, boolean activo, Date fechaServidor, 
                    Date fechaAlta, UsuarioDTO usuarioDTO){
    this.idPerfil = idPerfil;
    this.nombrePerfil = nombrePerfil;
    this.descripcionPerfil = descripcionPerfil;
    this.activo = activo;
    this.fechaServidor = fechaServidor;
    this.fechaAlta = fechaAlta;
    this.usuarioDTO = usuarioDTO;


  }

  //Setter y Getter
    public Integer getIdPerfil() {
       return idPerfil;
    }
            
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
            
    public String getNombrePerfil() {
        return nombrePerfil;
    }
            
    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
            
    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }
            
    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }
            
    public boolean getActivo() {
        return activo;
    }
            
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
            
    public Date getFechaAlta() {
         return fechaAlta;
    }
            
    public void setFechaAlta(Date fechaAlta) {
         this.fechaAlta = fechaAlta;
    }
           
    public Date getFechaServidor() {
        return fechaServidor;
    }
            
    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }
            
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
                
    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

   /* public AccesoDTO getAccesoDTO(){
        return accesoDTO;
    }

    public void setAccesoDTO(AccesoDTO accesoDTO){
        this.accesoDTO = accesoDTO;
    }*/
    //Método para crear un PerfilDTO a partir de una entidad Perfil
    public PerfilDTO fromPerfil(Perfil perfil){
       
        if(perfil != null && perfil.getUsuario() != null){
        //Convertir Usuario a UsuarioDTO
       
        UsuarioDTO usuarioDTO = UsuarioDTO.fromUsuario(perfil.getUsuario());
        
            return new PerfilDTO(
                perfil.getIdPerfil(),
                perfil.getNombrePerfil(),
                perfil.getDescripcionPerfil(),
                perfil.getActivo(),
                perfil.getFechaAlta(),
                perfil.getFechaServidor(),
            
                usuarioDTO
            );
        }
        return null;
    }
}

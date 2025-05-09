package com.example.demo.DTOs;
import java.util.Date;

import com.example.demo.model.Empresa;
import com.example.demo.model.Sucursal;

public class SucursalDTO {
    private Integer idSucursal;
    private String nombreSucursal;
    private String ciudad;
    private String estado;
    private boolean activo;
    private UsuarioDTO usuarioDTO;//Creamos instancia DTO usuario
    private EmpresaDTO empresaDTO;
    private String nombreEmpresa;
    //Constructor
    public SucursalDTO(Integer idSucursal,String nombreSucursal, String ciudad, String estado, boolean activo, UsuarioDTO usuarioDTO, EmpresaDTO empresaDTO){
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.ciudad = ciudad;
        this.activo = activo;
        this.estado = estado;
        this.nombreEmpresa = empresaDTO.getNombreEmpresa();
        this.usuarioDTO = usuarioDTO;

    }
   
    // Constructor vacío (sin parámetros) para Jackson
    public SucursalDTO() {
    }


        // Constructor que toma la entidad Sucursal
        public SucursalDTO(Sucursal sucursal) {
            this.idSucursal = sucursal.getIdSucursal();  // Asumiendo que tienes un getter para ID en Sucursal
            this.nombreSucursal = sucursal.getNombreSucursal();
            this.ciudad = sucursal.getCiudad();
            this.estado = sucursal.getEstado();
            this.activo = sucursal.getActivo();
            
    
            // Convertir a DTO            // Mapeo de la relación con UsuarioDTO y EmpresaDTO
        /*if (sucursal.getUsuario() != null) {
            this.usuarioDTO = UsuarioDTO.fromUsuario(sucursal.getUsuario());  // Convertimos la entidad Usuario en UsuarioDTO
        }

        if (sucursal.getEmpresa() != null) {
            this.empresaDTO = EmpresaDTO.fromEmpresa(sucursal.getEmpresa());  // Convertimos la entidad Empresa en EmpresaDTO
            this.nombreEmpresa = sucursal.getEmpresa().getNombreEmpresa();   // Usamos el nombre de la empresa si es necesario
        }*/
        }
        
    


    //Getters y setters
    public Integer getIdSucursal(){
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal){
        this.idSucursal = idSucursal;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public EmpresaDTO  getEmpresaDTO(){
        return empresaDTO;
    }

    public void setEmpresaDTO(EmpresaDTO empresaDTO){
        this.empresaDTO = empresaDTO;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

     // Método para crear un SucursalDTO a partir de una entidad Empresa
    public static SucursalDTO fromSucursal(Sucursal sucursal) {
      if (sucursal != null && sucursal.getUsuario() != null) {
        // Convertir Usuario a UsuarioDTO
        UsuarioDTO usuarioDTO = UsuarioDTO.fromUsuario(sucursal.getUsuario());
        EmpresaDTO empresaDTO = EmpresaDTO.fromEmpresa(sucursal.getEmpresa());
        return new SucursalDTO(
            sucursal.getIdSucursal(),
            sucursal.getNombreSucursal(),
            sucursal.getCiudad(),
            sucursal.getEstado(),
            sucursal.getActivo(),
            usuarioDTO, // Incluir UsuarioDTO en lugar de Usuario
            empresaDTO
            );
      }
      return null;
    }

}

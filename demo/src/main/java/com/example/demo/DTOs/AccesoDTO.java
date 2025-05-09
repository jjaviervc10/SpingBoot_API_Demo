package com.example.demo.DTOs;

import java.util.Date;

import com.example.demo.model.Modulo;

public class AccesoDTO {
    private Integer idAcceso;
    private Integer orden;
    private String nombreAcceso;
    private boolean activo;
    private Date fechaAlta;
    private Date fechaBaja;
    private ModuloDTO moduloDTO;

    //Contructor
    public AccesoDTO(Integer idAcceso, String nombreAcceso,Integer orden, boolean activo, 
                     Date fechaAlta, Date fechaBaja, ModuloDTO moduloDTO){
        this.idAcceso = idAcceso;
        this.nombreAcceso = nombreAcceso;
        this.orden = orden;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.moduloDTO = moduloDTO;
    }

      public Integer getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Integer idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getNombreAcceso() {
        return nombreAcceso;
    }

    public void setNombreAcceso(String nombreAcceso) {
        this.nombreAcceso = nombreAcceso;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public ModuloDTO getModuloDTO() {
        return moduloDTO;
    }

    public void setModuloDTO(ModuloDTO moduloDTO) {
        this.moduloDTO = moduloDTO;
    }
}

package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class rPerfilAccesoId implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAcceso")
    private Integer idAcceso;

    @Basic(optional = false)
    @NotNull
    @Column( name = "idPerfil")
    private Integer idPerfil;

    // Constructor, getters y setters

    public rPerfilAccesoId() {}

    public rPerfilAccesoId(Integer idPerfil, Integer idAcceso) {
        this.idAcceso = idAcceso;
        this.idPerfil = idPerfil;
    }

    // Getters y setters
    public Integer getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Integer idAcceso) {
        this.idAcceso = idAcceso;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

}

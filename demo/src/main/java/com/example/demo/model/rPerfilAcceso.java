package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rPerfilAcceso")
public class rPerfilAcceso {
 @EmbeddedId
    private rPerfilAccesoId id;

    //@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @JoinColumn(name = "\"idUsuario\"", referencedColumnName = "\"idUsuario\"", nullable = false)
    @ManyToOne
    //@JsonBackReference // Evita la recursividad aquí
    //@JsonManagedReference
   @JsonIgnore
    private Usuario usuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaServidor")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;

    public rPerfilAcceso(){

    }

    public rPerfilAcceso(rPerfilAccesoId id, Date fechaServidor) {
        this.id = id;
        this.fechaServidor = fechaServidor;
    }

    // Constructor con los tres parámetros (idPerfil, idAcceso, idUsuario)
    public rPerfilAcceso(Integer idPerfil, Integer idAcceso) {
        this.id = new rPerfilAccesoId(idPerfil, idAcceso);  // Crear la clave compuesta
        this.fechaServidor = new Date();  // O puedes asignar la fecha actual
    }

    public rPerfilAccesoId getId() {
        return id;
    }

    public void setId(rPerfilAccesoId id) {
        this.id = id;
    }
 

public Date getFechaServidor() {
    return fechaServidor;
}

public void setFechaServidor(Date fechaServidor) {
    this.fechaServidor = fechaServidor;
}

public Usuario getUsuario(){
    return usuario;
 }

 public void setUsuario(Usuario usuario){
     this.usuario = usuario;
 }
}

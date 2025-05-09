package com.example.demo.model;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//import jakarta.validation.constraints.NotNull; 
import jakarta.persistence.*;


@Entity
@Table(name = "cSucursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column (name = "idSucursal")
    private Integer idSucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreSucursal") 
    private String nombreSucursal;
    @Basic(optional = false)
    @Size(min = 1, max = 50)  
    @NotNull
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name="estado")
    private String estado;
    @Basic(optional  = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional =  false)
    @NotNull
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaServidor")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa", nullable = true)
    @JsonManagedReference
    private Empresa idEmpresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",nullable = false)
    @JsonManagedReference // Evita la recursividad aquí
    private Usuario idUsuario;  // Esta es la propiedad que debe existir en la entidad Empresa


     public Sucursal(){}

     public Sucursal(Integer idSucursal){this.idSucursal = idSucursal;}

     public Sucursal(Integer idSucursal, String nombreSucursal, String ciudad, String estado, boolean activo, Date fechaAlta, Date fechaServidor )
     {
        this.idSucursal =  idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.ciudad = ciudad;
        this.estado = estado;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaServidor =fechaServidor;
     }

     public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
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

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public Empresa getEmpresa(){
        return idEmpresa;
    }

    public void setEmpresa (Empresa idEmpresa){
        this.idEmpresa = idEmpresa;
    }

    public Usuario getUsuario(){
        return idUsuario;
     }
 
     public void setUsuario(Usuario idUsuario){
         this.idUsuario = idUsuario;
     }

}

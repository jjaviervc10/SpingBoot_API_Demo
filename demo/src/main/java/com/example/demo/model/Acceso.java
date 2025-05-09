package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sAcceso")
public class Acceso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAcceso")
  private Integer idAcceso;

   @Basic(optional = false)
   @NotNull
   @Size(min = 1, max = 50)
   @Column(name = "nombreAcceso")
   private String nombreAcceso;

   @Column(name = "orden")
   private Integer orden;
   @Basic(optional = false)
   @NotNull
   @Column(name = "activo")
   private boolean activo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
   
  
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idModulo", referencedColumnName =  "idModulo", nullable = false)
    @JsonBackReference
    private Modulo idModulo;

    public Acceso(){}

    public Acceso(Integer idAcceso){
        this.idAcceso = idAcceso;
    }

    public Acceso(Integer idAcceso, String nombreAcceso, boolean activo, Date fechaAlta){
        this.idAcceso = idAcceso;
        this.nombreAcceso = nombreAcceso;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
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

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
    }
}
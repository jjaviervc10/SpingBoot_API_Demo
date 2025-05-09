package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="sPerfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerfil")
    private Integer idPerfil;
    
    @Size(min = 0 , max = 50)
    @Column(name = "nombrePerfil")
    private String nombrePerfil;

    @Size(min = 0, max = 150)
    @Column(name = "descripcionPerfil")
     private String descripcionPerfil;

     @Basic(optional = false)
     @NotNull
     @Column (name = "activo")
     private boolean activo;

     @Basic(optional = false)
     @NotNull
     @Column ( name = "fechaAlta")
     @Temporal(TemporalType.TIMESTAMP)
     private Date fechaAlta;

     @Column( name = "fechaBaja")
     @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaServidor")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;

   @ManyToOne
   @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario",nullable = false)
   @JsonManagedReference // Evita la recursividad aquí
   private Usuario idUsuario;

   //Relacion muchos a muchos con SAcceso
   
   public Perfil(){}

   public Perfil(Integer idPerfil){
    this.idPerfil = idPerfil;
   }

   public Perfil(Integer idPerfil, String nombrePerfil, String descripcionPerfil, Date fechaAlta,
                 Date fechaServidor, boolean activo){
                    this.idPerfil = idPerfil;
                    this.nombrePerfil = nombrePerfil;
                    this.descripcionPerfil = descripcionPerfil;
                    this.activo = activo;
                    this.fechaAlta = fechaAlta;
                    this.fechaServidor = fechaServidor;
                 }

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
            
                public Usuario getUsuario() {
                    return idUsuario;
                }
            
                public void setUsuario(Usuario idUsuario) {
                    this.idUsuario = idUsuario;
                }

}

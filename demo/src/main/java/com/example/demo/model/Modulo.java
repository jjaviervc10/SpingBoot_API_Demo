package com.example.demo.model;

import java.util.Date;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*; // Importaciones de JPA
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "sModulo")
public class Modulo {
 @Id
 @Basic(optional = false)
 @NotNull
 @Column(name = "idModulo")
 private Integer idModulo;

 @Basic(optional = false)
 @NotNull
 @Size(min = 1, max = 50)
 @Column(name ="nombreModulo")
 private String nombreModulo;

 @Size(min=0, max=50)
 @Column(name = "icono")
 private String icono;

 @Size(min = 0, max= 50)
 @Column(name = "url")
 private String url;

 @Column(name = "orden")
 private Integer orden;
 @Basic (optional = false)
@NotNull
 @Column(name = "activo")
 private boolean activo;

 @Basic(optional = false)
 @NotNull
 @Column(name="fechaAlta")
 @Temporal(TemporalType.TIMESTAMP)
 private Date fechaAlta;

 @Column (name  = "fechaBaja")
@Temporal(TemporalType.TIMESTAMP)
private Date fechaBaja;

@OneToMany(mappedBy = "idModulo", fetch = FetchType.LAZY)
@JsonManagedReference
private Collection<Acceso> AccesoCollection;

public Modulo(){}

public Modulo(Integer idModulo){
    this.idModulo = idModulo;

}

public Modulo(Integer idModulo, String nombreModulo, boolean activo, 
              Date fechaAlta)
              {
                this.idModulo = idModulo;
                this.nombreModulo = nombreModulo;
                this.activo = activo;
                this.fechaAlta = fechaAlta;
              }
              public Integer getIdModulo() {
                return idModulo;
            }
        
            public void setIdModulo(Integer idModulo) {
                this.idModulo = idModulo;
            }
        
            public String getNombreModulo() {
                return nombreModulo;
            }
        
            public void setNombreModulo(String nombreModulo) {
                this.nombreModulo = nombreModulo;
            }
        
            public String getIcono() {
                return icono;
            }
        
            public void setIcono(String icono) {
                this.icono = icono;
            }
        
            public String getUrl() {
                return url;
            }
        
            public void setUrl(String url) {
                this.url = url;
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
        
            public Collection<Acceso> getAccesos(){
                return AccesoCollection;
            }

            public void  setAccesos(Collection<Acceso> AccesoCollection){
                this.AccesoCollection =AccesoCollection;
            }


}

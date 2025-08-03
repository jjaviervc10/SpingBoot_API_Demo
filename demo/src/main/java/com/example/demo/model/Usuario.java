package com.example.demo.model;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "\"sUsuario\"")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idUsuario\"")
    private Integer idUsuario;
    @Basic(optional = false)  
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "\"usuario\"")
    private String usuario;
    @Basic(optional = false)  
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "\"pass\"")
    private String pass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "\"nombreCompleto\"")
    private String nombreCompleto;
    @Size(min = 0,max = 50)
    @Column(name = "\"correo\"")
    private String correo;
    @Size(min = 0, max = 10)
    @Column(name = "\"telefono\"")
    private String telefono;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonBackReference // Evita la recursividad aquí 
    private List<Empresa> empresas;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonBackReference // Evita la recursividad aquí 
    private List<Sucursal> sucursales;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonBackReference // Evita la recursividad aquí 
    private List<Perfil> perfiles;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
   // @JsonManagedReference // Evita la recursión infinita en el otro lado de la relación
   @JsonBackReference // Evita la recursividad aquí
   //@JsonIgnore
   private List<rPerfilAcceso> rPerfilAccesoList;



    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    
    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Perfil> getPerfiles(){
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles){
       this.perfiles = perfiles;
    }

    public List<rPerfilAcceso> getrPerfilAccesos() {
        return rPerfilAccesoList;
    }

    public void setrPerfilAccesos(List<rPerfilAcceso> rPerfilAccesoList) {
        this.rPerfilAccesoList = rPerfilAccesoList;
    }
}

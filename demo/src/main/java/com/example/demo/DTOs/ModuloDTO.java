package com.example.demo.DTOs;

import java.util.Date;

public class ModuloDTO {
   private Integer idModulo;
   private boolean activo;
   private String nombreModulo;
   private Date fechaAlta;

   //Constructor
   public ModuloDTO(Integer idModulo, boolean activo, String nombreModulo, Date fechaAlta){
          this.idModulo = idModulo;
          this.activo = activo;
          this.nombreModulo = nombreModulo;
          this.fechaAlta = fechaAlta;

   }
   //Getters y setters

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


}

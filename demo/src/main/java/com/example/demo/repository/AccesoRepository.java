package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Acceso;

public interface AccesoRepository  extends JpaRepository<Acceso, Integer> {
    List<Acceso> findByNombreAcceso(String nombre);

   /* @Query("SELECT a FROM Acceso a WHERE a.idAcceso IN (" +
           "SELECT rpa.id.idAcceso FROM rPerfilAcceso rpa " +
           "WHERE rpa.id.idPerfil = :idPerfil)")
    List<Acceso> findByPerfil(@Param("idPerfil") Long idPerfil);]*/

}

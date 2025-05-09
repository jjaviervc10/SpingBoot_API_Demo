package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Acceso;
import com.example.demo.model.rPerfilAcceso;
import com.example.demo.model.rPerfilAccesoId;

@Repository
public interface RPerfilAccesoRepository extends JpaRepository<rPerfilAcceso, rPerfilAccesoId> {
    // Consulta personalizada para obtener rPerfilAcceso por idAcceso e idPerfil
    Optional<rPerfilAcceso> findById(rPerfilAccesoId id);

    // Cambiar la consulta para acceder correctamente a las propiedades dentro de rPerfilAccesoId
    List<rPerfilAcceso> findById_idAccesoAndId_idPerfil(Integer idAcceso, Integer idPerfil);
 
      // Método para encontrar accesos asignados a un perfil
    List<rPerfilAcceso> findById_idPerfil(Integer idPerfil);


        // Consulta para obtener los accesos disponibles (accesos que no están asignados a un perfil)
    //@Query("SELECT a FROM sAcceso a WHERE a.idAcceso NOT IN (SELECT rpa.id.idAcceso FROM rPerfilAcceso rpa WHERE rpa.id.idPerfil = :idPerfil AND rpa.id.idAcceso IS NOT NULL)")
    @Query("SELECT a FROM Acceso a WHERE a.idAcceso NOT IN (SELECT rpa.id.idAcceso FROM rPerfilAcceso rpa WHERE rpa.id.idPerfil = :idPerfil AND rpa.id.idAcceso IS NOT NULL)")
    List<Acceso> findAccesosDisponiblesParaPerfil(Integer idPerfil);

    @Query("SELECT DISTINCT a FROM Acceso a " +
    "WHERE a.idAcceso IN (" +
    "SELECT rpa.id.idAcceso FROM rPerfilAcceso rpa " +
    "WHERE rpa.id.idPerfil = :idPerfil AND rpa.id.idAcceso IS NOT NULL)")
       List<Acceso> findById_IdPerfilAndId_IdAcceso(@Param("idPerfil") Integer idPerfil);
   

}

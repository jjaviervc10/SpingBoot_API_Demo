package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.PerfilDTO;
import com.example.demo.model.Perfil;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PerfilRepository;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {
   @Autowired
   private PerfilRepository perfilRepository;

   //Endpoint para consultar todos los perfiles
   @GetMapping
   public List<Perfil> getAllPerfiles(){
    return perfilRepository.findAll();
   }

   //Endpoint creado para consultar un perfil por su ID
   @GetMapping("/{id}")
   public ResponseEntity<Perfil> getPerfilById(@PathVariable Integer id){
      Optional<Perfil> perfil = perfilRepository.findById(id);
      return perfil.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
   }

   @PostMapping
    public ResponseEntity<Perfil> createPerfil(@RequestBody PerfilDTO perfilDTO){
      //Crear una nueva entidad Perfil a partir del DTO
      Perfil perfil = new Perfil();
      perfil.setActivo(perfilDTO.getActivo());
      perfil.setDescripcionPerfil(perfilDTO.getDescripcionPerfil());
      perfil.setNombrePerfil(perfilDTO.getNombrePerfil());

      //Asignamos un Usuario con id 5 (o cualquier valor dinamico que se prefiera)
      Usuario usuario  = new Usuario();
      usuario.setIdUsuario(5);

      perfil.setUsuario(usuario);

      perfil.setFechaAlta(new Date());
      perfil.setFechaServidor(new Date());

      //Guardar perfil
      Perfil savedPerfil = perfilRepository.save(perfil);

      //Retornamos la respuesta con el nuevo registro de Perfil
      return ResponseEntity.status(HttpStatus.CREATED).body(savedPerfil);

    } 

   //Endpoint para actualizar un perfil existente
   @PutMapping("/{id}")
public ResponseEntity<Perfil> updatePerfil(@PathVariable Integer id, @RequestBody PerfilDTO perfilDTO) {
    Optional<Perfil> existingPerfil = perfilRepository.findById(id);
    if(existingPerfil.isPresent()){
        Perfil perfil = existingPerfil.get();
        // Aquí actualizas solo los campos que quieres modificar.
        perfil.setNombrePerfil(perfilDTO.getNombrePerfil());
        perfil.setDescripcionPerfil(perfilDTO.getDescripcionPerfil());
        perfil.setActivo(perfilDTO.getActivo());
        
        // Guarda el perfil actualizado
        Perfil updatedPerfil = perfilRepository.save(perfil);
        return ResponseEntity.ok(updatedPerfil);
    }
    return ResponseEntity.notFound().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePerfil(@PathVariable Integer id) {
    Optional<Perfil> perfil = perfilRepository.findById(id);
    if (perfil.isPresent()) {
        perfilRepository.deleteById(id); // Elimina el perfil por su ID
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content si la eliminación fue exitosa
    }
    return ResponseEntity.notFound().build(); // Devuelve un 404 Not Found si el perfil no existe
}

  /*  @PutMapping("/{id}")
    public ResponseEntity<Perfil> updatePerfil (@PathVariable Integer id, @RequestBody Perfil perfil){
      Optional <Perfil> existingPerfil = perfilRepository.findById(id);
      if(existingPerfil.isPresent()){
         perfil.setIdPerfil(id);
         Perfil updatePerfil = perfilRepository.save(perfil);
         return ResponseEntity.ok(updatePerfil);
      }
      return ResponseEntity.notFound().build();
    }*/
}

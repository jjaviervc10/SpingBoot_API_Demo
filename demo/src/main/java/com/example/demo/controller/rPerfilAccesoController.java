package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.DTOs.rPerfilAccesoDTO;
import com.example.demo.model.Acceso;
import com.example.demo.model.Usuario;
import com.example.demo.model.rPerfilAcceso;
import com.example.demo.model.rPerfilAccesoId;
import com.example.demo.repository.RPerfilAccesoRepository;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/rperfilaccesos")
public class rPerfilAccesoController {

    @Autowired
    private RPerfilAccesoRepository rPerfilAccesoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
public List<rPerfilAccesoDTO> getAllRPerfilAcceso() {
    return rPerfilAccesoRepository.findAll()
                                  .stream()
                                  .map(rPerfilAccesoDTO::new)  // Aquí mapeamos a rPerfilAccesoDTO
                                  .collect(Collectors.toList());
}

    // Endpoint para obtener un rPerfilAcceso por idPerfil e idAcceso
    /*@GetMapping("/{idPerfil}/{idAcceso}")
    public ResponseEntity<rPerfilAcceso> getRPerfilAccesoById(@PathVariable Integer idPerfil, @PathVariable Integer idAcceso) {
        rPerfilAccesoId id = new rPerfilAccesoId(idPerfil, idAcceso);
        Optional<rPerfilAcceso> rPerfilAcceso = rPerfilAccesoRepository.findById(id);
        return rPerfilAcceso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/


 /*  @GetMapping("/{idPerfil}/{idAcceso}")
    public ResponseEntity<rPerfilAccesoDTO> getRPerfilAccesoById(@PathVariable Integer idPerfil, @PathVariable Integer idAcceso) {
        // Buscar el rPerfilAcceso por idPerfil e idAcceso
        Optional<rPerfilAcceso> optionalRPerfilAcceso = rPerfilAccesoRepository.findById(new rPerfilAccesoId(idAcceso, idPerfil));
        
        // Si no se encuentra el rPerfilAcceso, devolver un 404 (Not Found)
        if (!optionalRPerfilAcceso.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        // Obtener la entidad rPerfilAcceso de la base de datos
        rPerfilAcceso rPerfilAcceso = optionalRPerfilAcceso.get();
    
        // Mapear la entidad a rPerfilAccesoDTO
        rPerfilAccesoDTO rPerfilAccesoDTO = new rPerfilAccesoDTO(rPerfilAcceso);
        
        // Devolver el DTO en la respuesta con código de estado 200 OK
        return ResponseEntity.ok(rPerfilAccesoDTO);
    }*/
    @GetMapping("/{idPerfil}/{idAcceso}")
public ResponseEntity<rPerfilAccesoDTO> getRPerfilAccesoById(@PathVariable Integer idPerfil, @PathVariable Integer idAcceso) {
    // Buscar el rPerfilAcceso por idPerfil e idAcceso
    Optional<rPerfilAcceso> optionalRPerfilAcceso = rPerfilAccesoRepository.findById(new rPerfilAccesoId(idPerfil, idAcceso));
    
    // Si no se encuentra, retornar un 404 (Not Found)
    if (!optionalRPerfilAcceso.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    
    // Mapear el rPerfilAcceso a rPerfilAccesoDTO
    rPerfilAcceso rPerfilAcceso = optionalRPerfilAcceso.get();
    rPerfilAccesoDTO rPerfilAccesoDTO = new rPerfilAccesoDTO(
        rPerfilAcceso,
        new UsuarioDTO(
            rPerfilAcceso.getUsuario().getIdUsuario(),
            rPerfilAcceso.getUsuario().getNombreCompleto(),
            rPerfilAcceso.getUsuario().getCorreo(),
            rPerfilAcceso.getUsuario().getTelefono(),
            rPerfilAcceso.getUsuario().getUsuario(),
            rPerfilAcceso.getUsuario().getPass()
        ),
        rPerfilAcceso.getFechaServidor()
    );
    
    // Devolver el DTO en la respuesta
    return ResponseEntity.ok(rPerfilAccesoDTO);
}

@PutMapping("/{idPerfil}/{idAcceso}")
public ResponseEntity<rPerfilAcceso> updateRPerfilAcceso(
    @PathVariable Integer idPerfil, @PathVariable Integer idAcceso, @RequestBody rPerfilAccesoDTO rPerfilAccesoDTO) {
    
    // Mapear los datos del DTO
    rPerfilAccesoId id = new rPerfilAccesoId(idPerfil, idAcceso);
    Optional<rPerfilAcceso> existingRPerfilAcceso = rPerfilAccesoRepository.findById(id);

    if (existingRPerfilAcceso.isPresent()) {
        rPerfilAcceso rPerfilAcceso = existingRPerfilAcceso.get();
        rPerfilAcceso.setFechaServidor(new Date());

        // Buscar el usuario por idUsuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(rPerfilAccesoDTO.getUsuarioDTO().getIdUsuario());
        
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            rPerfilAcceso.setUsuario(usuario);
            
            // Guardar el perfil de acceso actualizado
            rPerfilAcceso updatedRPerfilAcceso = rPerfilAccesoRepository.save(rPerfilAcceso);
            return ResponseEntity.ok(updatedRPerfilAcceso);
        } else {
            return ResponseEntity.badRequest().body(null); // Si no se encuentra el usuario, devuelve un 400
        }
    }

    return ResponseEntity.notFound().build();
}

@PostMapping("/")
public ResponseEntity<rPerfilAcceso> createRPerfilAcceso(@RequestBody rPerfilAccesoDTO rPerfilAccesoDTO) {
    // Buscar el Usuario por el idUsuario en el DTO
    Optional<Usuario> usuarioOptional = usuarioRepository.findById(rPerfilAccesoDTO.getIdUsuario());
    
    if (!usuarioOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Si el usuario no existe, devuelve error 404
    }
    
    // Crear un nuevo objeto rPerfilAcceso
    rPerfilAcceso nuevoRPerfilAcceso = new rPerfilAcceso();
    
    // Asignar los valores al nuevo objeto rPerfilAcceso
    rPerfilAccesoId id = new rPerfilAccesoId(rPerfilAccesoDTO.getIdPerfil(), rPerfilAccesoDTO.getIdAcceso());
    nuevoRPerfilAcceso.setId(id);
    nuevoRPerfilAcceso.setFechaServidor(new Date());
    
    // Asignar el Usuario al rPerfilAcceso
    nuevoRPerfilAcceso.setUsuario(usuarioOptional.get());
    
    // Guardar el rPerfilAcceso en la base de datos
    rPerfilAcceso savedRPerfilAcceso = rPerfilAccesoRepository.save(nuevoRPerfilAcceso);
    
    // Devolver la respuesta con el objeto creado
    return ResponseEntity.status(HttpStatus.CREATED).body(savedRPerfilAcceso);
}


/*@PostMapping("/")
public ResponseEntity<rPerfilAcceso> createRPerfilAcceso(@RequestBody rPerfilAccesoDTO rPerfilAccesoDTO) {
    // Buscar el Usuario por el idUsuario en el DTO
    Optional<Usuario> usuarioOptional = usuarioRepository.findById(rPerfilAccesoDTO.getUsuarioDTO().getIdUsuario());
    
    if (!usuarioOptional.isPresent()) {
        return ResponseEntity.badRequest().body(null);  // Si el usuario no existe, devuelve un error 400
    }
    
    // Crear un nuevo objeto rPerfilAcceso
    rPerfilAcceso nuevoRPerfilAcceso = new rPerfilAcceso();
    
    // Asignar los valores al nuevo objeto rPerfilAcceso
    rPerfilAccesoId id = new rPerfilAccesoId(rPerfilAccesoDTO.getIdPerfil(), rPerfilAccesoDTO.getIdAcceso());
    nuevoRPerfilAcceso.setId(id);
    nuevoRPerfilAcceso.setFechaServidor(new Date());
    
    // Asignar el Usuario al rPerfilAcceso
    nuevoRPerfilAcceso.setUsuario(usuarioOptional.get());
    
    // Guardar el rPerfilAcceso en la base de datos
    rPerfilAcceso savedRPerfilAcceso = rPerfilAccesoRepository.save(nuevoRPerfilAcceso);
    
    // Devolver la respuesta con el objeto creado
    return ResponseEntity.status(HttpStatus.CREATED).body(savedRPerfilAcceso);
}*/


  
    // Endpoint para actualizar un rPerfilAcceso existente
   /* @PutMapping("/{idPerfil}/{idAcceso}")
    public ResponseEntity<rPerfilAcceso> updateRPerfilAcceso(
        @PathVariable Integer idPerfil, @PathVariable Integer idAcceso, @RequestBody rPerfilAccesoDTO rPerfilAccesoDTO) {
        
        // Mapear los datos del DTO
        rPerfilAccesoId id = new rPerfilAccesoId(idPerfil, idAcceso);
        Optional<rPerfilAcceso> existingRPerfilAcceso = rPerfilAccesoRepository.findById(id);

        if (existingRPerfilAcceso.isPresent()) {
            rPerfilAcceso rPerfilAcceso = existingRPerfilAcceso.get();
            rPerfilAcceso.setFechaServidor(rPerfilAccesoDTO.getFechaServidor());

            // Convertir UsuarioDTO a Usuario
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(5); // Establecer el idUsuario que deseas asignar
            rPerfilAcceso.setUsuario(usuario);

            // Guardar el perfil de acceso actualizado
            rPerfilAcceso updatedRPerfilAcceso = rPerfilAccesoRepository.save(rPerfilAcceso);
            return ResponseEntity.ok(updatedRPerfilAcceso);
        }

        return ResponseEntity.notFound().build();
    }*/

    // Endpoint para eliminar un rPerfilAcceso
    @DeleteMapping("/{idPerfil}/{idAcceso}")
    public ResponseEntity<Void> deleteRPerfilAcceso(@PathVariable Integer idPerfil, @PathVariable Integer idAcceso) {
        rPerfilAccesoId id = new rPerfilAccesoId(idPerfil, idAcceso);
        Optional<rPerfilAcceso> rPerfilAcceso = rPerfilAccesoRepository.findById(id);

        if (rPerfilAcceso.isPresent()) {
            rPerfilAccesoRepository.delete(rPerfilAcceso.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // Endpoint para obtener los accesos disponibles para un perfil específico
    @GetMapping("/accesosDisponibles/{idPerfil}")
    public ResponseEntity<List<Acceso>> getAccesosDisponibles(@PathVariable Integer idPerfil) {
        List<Acceso> accesosDisponibles = rPerfilAccesoRepository.findAccesosDisponiblesParaPerfil(idPerfil);
        return ResponseEntity.ok(accesosDisponibles);
    }

 
}

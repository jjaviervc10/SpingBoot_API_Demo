package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
    //List<Usuario> findByNombreUsuario(String nombre);
 //  List<Usuario> findByUsuario(String usuario);
   
    // Este es el método correcto para encontrar al Usuario por idUsuario
    Optional<Usuario> findById(Integer idUsuario);

       // Método para encontrar un usuario por su nombre de usuario, garantizando que el resultado es único
       Optional<Usuario> findByUsuario(String usuario);  
}

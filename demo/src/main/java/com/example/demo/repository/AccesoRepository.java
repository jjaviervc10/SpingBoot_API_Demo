package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Acceso;

public interface AccesoRepository  extends JpaRepository<Acceso, Integer> {
    List<Acceso> findByNombreAcceso(String nombre);

}

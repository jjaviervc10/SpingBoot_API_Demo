package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    List<Empresa> findByNombreEmpresa(String nombre);
}

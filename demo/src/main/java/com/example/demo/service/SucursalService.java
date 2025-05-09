package com.example.demo.service;

import com.example.demo.DTOs.SucursalDTO;
import com.example.demo.model.Sucursal;
import com.example.demo.repository.SucursalRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

   // Método para obtener todas las sucursales
    @Transactional
    public List<SucursalDTO> obtenerTodasSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();  // Recuperamos todas las sucursales
        return sucursales.stream()
                         .map(SucursalDTO::new)  // Convertimos cada Sucursal a su DTO correspondiente
                         .collect(Collectors.toList());  // Devolvemos la lista de DTOs
    }

    // Método para obtener una sucursal por ID
    @Transactional
    public SucursalDTO obtenerSucursal(Integer id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada"));
        return new SucursalDTO(sucursal);  // Devolvemos el DTO de la sucursal
    }
}

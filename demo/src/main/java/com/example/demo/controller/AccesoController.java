package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Acceso;
import com.example.demo.repository.AccesoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/accesos")
public class AccesoController {

    @Autowired
    private AccesoRepository accesoRepository;
    
    //Endpoint para consultar todos los accesos
    @GetMapping
    public List<Acceso> getAllAccesos(){
        return accesoRepository.findAll();
    }
}

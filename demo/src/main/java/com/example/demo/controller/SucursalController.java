package com.example.demo.controller;

import com.example.demo.DTOs.EmpresaDTO;
import com.example.demo.DTOs.SucursalDTO;
import com.example.demo.model.Empresa;
import com.example.demo.model.Sucursal;
import com.example.demo.model.Usuario;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.service.SucursalService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {
    
    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping
    public List<SucursalDTO> getAllSucursals(){
        List<Sucursal> sucursales = sucursalRepository.findAll();

        //Convertimos la lista de entidades a lista DTOs
       return sucursales.stream()
                        .map(SucursalDTO::fromSucursal)
                        .collect(Collectors.toList());
    }    

    //endpoint para obtener una sucursal por ID
    /*@GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Integer id){
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        return sucursal.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());

    }*/

    @GetMapping("/{id}")
public ResponseEntity<SucursalDTO> getSucursalById(@PathVariable Integer id) {
    Optional<Sucursal> sucursalOpt = sucursalRepository.findById(id);
    return sucursalOpt.map(sucursal -> ResponseEntity.ok(SucursalDTO.fromSucursal(sucursal)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
}


    @PostMapping
    public ResponseEntity<Sucursal> createSucursal(@RequestBody SucursalDTO sucursalDTO){
        //Crear una nueva entiddad Sucursal apartir del DTO
        Sucursal sucursal = new Sucursal();
        sucursal.setNombreSucursal(sucursalDTO.getNombreSucursal());
        sucursal.setCiudad(sucursalDTO.getCiudad());
        sucursal.setActivo(sucursalDTO.getActivo());
        sucursal.setEstado(sucursalDTO.getEstado());

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(5);

        sucursal.setUsuario(usuario);
        
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(285);
        sucursal.setEmpresa(empresa);

        sucursal.setFechaAlta(new Date());
        sucursal.setFechaServidor(new Date());

        Sucursal savedSucursal = sucursalRepository.save(sucursal);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSucursal);
        
    }

    //Endpoint para eliminar una empresa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Integer id){
        Optional<Sucursal> existingSucursal = sucursalRepository.findById(id);
        if(existingSucursal.isPresent()){
            sucursalRepository.deleteById(id);
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
   

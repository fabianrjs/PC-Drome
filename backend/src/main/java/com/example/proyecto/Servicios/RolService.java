package com.example.proyecto.Servicios;

import java.util.Optional;

import com.example.proyecto.Modelo.Rol;
import com.example.proyecto.Repositorios.RolRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    
    @Autowired
    private RolRepo rolRepo;

    public <S extends Rol> S save(S entity) {
        return rolRepo.save(entity);
    }

    public Optional<Rol> findById(Long id) {
        return rolRepo.findById(id);
    }
}

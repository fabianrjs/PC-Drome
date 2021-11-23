package com.example.proyecto.Servicios;

import com.example.proyecto.Modelo.Carrito;
import com.example.proyecto.Repositorios.CarritoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepo carritoRepo;

    public <S extends Carrito> S save(S entity) {
        return carritoRepo.save(entity);
    }
}

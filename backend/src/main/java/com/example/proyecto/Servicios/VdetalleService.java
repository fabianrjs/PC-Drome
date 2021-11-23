package com.example.proyecto.Servicios;

import com.example.proyecto.Modelo.VentaDetalle;
import com.example.proyecto.Repositorios.VdetalleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VdetalleService {
    
    @Autowired
    private VdetalleRepo vdetalleRepo;

    public <S extends VentaDetalle> S save(S entity) {
        return vdetalleRepo.save(entity);
    }
}

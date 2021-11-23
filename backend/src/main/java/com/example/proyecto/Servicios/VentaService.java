package com.example.proyecto.Servicios;

import java.util.Optional;

import com.example.proyecto.Modelo.Venta;
import com.example.proyecto.Repositorios.VentaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VentaService{
    
    @Autowired
    private VentaRepo ventaRepo;

    public <S extends Venta> S save(S entity) {
        return ventaRepo.save(entity);
    }

    public Optional<Venta> findById(Long id) {
        return ventaRepo.findById(id);
    }

    public void deleteById(Long id) {
        ventaRepo.deleteById(id);
    }

    public Page<Venta> getVentas(Pageable pageable) {
        return ventaRepo.getVentas(pageable);
    }

    public Page<Venta> getVentasById(long id,Pageable pageable) {
        return ventaRepo.getVentasById(id,pageable);
    }

}

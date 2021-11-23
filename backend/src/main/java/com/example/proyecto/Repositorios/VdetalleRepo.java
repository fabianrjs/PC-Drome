package com.example.proyecto.Repositorios;

import com.example.proyecto.Modelo.VentaDetalle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VdetalleRepo extends CrudRepository<VentaDetalle,Long>{
    
}

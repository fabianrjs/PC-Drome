package com.example.proyecto.Repositorios;

import com.example.proyecto.Modelo.CarritoDetalle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoDRepo extends CrudRepository<CarritoDetalle,Long>{
    
}

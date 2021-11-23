package com.example.proyecto.Repositorios;

import com.example.proyecto.Modelo.Carrito;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepo extends CrudRepository<Carrito,Long>{
    
}

package com.example.proyecto.Repositorios;

import com.example.proyecto.Modelo.Rol;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepo extends CrudRepository<Rol,Long>{
    
}

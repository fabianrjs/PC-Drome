package com.example.proyecto.Repositorios;


import com.example.proyecto.Modelo.Venta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepo extends CrudRepository<Venta,Long> {
    
    @Query(value = "SELECT v FROM Venta v")
    Page<Venta> getVentas(Pageable pageable);

    @Query(value = "SELECT v FROM Venta v WHERE v.usuario.id = ?1")
    Page<Venta> getVentasById(long id,Pageable pageable);

    
}
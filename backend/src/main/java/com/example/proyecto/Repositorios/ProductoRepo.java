package com.example.proyecto.Repositorios;

import javax.transaction.Transactional;

import com.example.proyecto.Modelo.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends CrudRepository<Producto,Long>{
    
    @Query(value = "SELECT p FROM Producto p")
    Page<Producto> getProductos(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Producto p SET p.nombre = :nombre, p.precio = :precio, p.marca = :marca, p.descripcion = :desc, p.tipoProducto = :tipoProducto, p.foto = :foto WHERE p.id = :id")
    void updateProductos(Long id, String nombre,int precio, String marca, String desc, String tipoProducto, String foto);
}

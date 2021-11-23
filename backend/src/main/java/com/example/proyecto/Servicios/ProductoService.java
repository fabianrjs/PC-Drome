package com.example.proyecto.Servicios;

import java.util.Optional;

import com.example.proyecto.Modelo.Producto;
import com.example.proyecto.Repositorios.ProductoRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepo productoRepo;

    public <S extends Producto> S save(S entity) {
        return productoRepo.save(entity);
    }

    public Optional<Producto> findById(Long id) {
        return productoRepo.findById(id);
    }

    public void deleteById(Long id) {
        productoRepo.deleteById(id);
    }

    public Page<Producto> getProductos(Pageable pageable) {
        return productoRepo.getProductos(pageable);
    }

    public void updateProductos(Long id, String nombre, int precio, String marca, String desc, String tipoProducto, String foto) {
        productoRepo.updateProductos(id, nombre, precio, marca, desc,tipoProducto, foto);  
    }
    
}

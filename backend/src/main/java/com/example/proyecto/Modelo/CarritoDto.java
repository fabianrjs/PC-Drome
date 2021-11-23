package com.example.proyecto.Modelo;

import java.util.List;

public class CarritoDto {
    
    private Long id;
    private Long idUsuario;
    private List<Long> productos;

    public CarritoDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Long> productos) {
        this.productos = productos;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


}

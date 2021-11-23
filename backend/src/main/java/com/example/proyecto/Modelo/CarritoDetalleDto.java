package com.example.proyecto.Modelo;

public class CarritoDetalleDto {
    
    private Long id;
    private Long producto;
    private int cantidad;


    public CarritoDetalleDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProducto() {
        return this.producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}

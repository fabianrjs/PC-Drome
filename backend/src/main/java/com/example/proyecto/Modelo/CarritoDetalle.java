package com.example.proyecto.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CarritoDetalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_carritoD")
    @SequenceGenerator(name = "seq_carritoD",initialValue = 1600)
    private Long id;

    @ManyToOne
    private Producto producto;
    
    @Column(nullable = false)
    private int cantidad;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public CarritoDetalle() {
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}

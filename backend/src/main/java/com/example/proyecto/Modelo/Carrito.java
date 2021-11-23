package com.example.proyecto.Modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Carrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_carritos")
    @SequenceGenerator(name = "seq_carritos",initialValue = 1500)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @OneToMany
    private List<CarritoDetalle> productos;
    
    public Carrito() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void addProducto(CarritoDetalle cDetalle){
        this.productos.add(cDetalle);
    }

    public List<CarritoDetalle> getProductos() {
        return this.productos;
    }

    public void setProductos(List<CarritoDetalle> productos) {
        this.productos = productos;
    }


}

package com.example.proyecto.Modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_usuarios")
    @SequenceGenerator(name = "seq_usuarios",initialValue = 1000)
    private Long id;
    @Basic
    @Column(nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(nullable = false, unique = true)
    private String correo;
    @Basic
    @Column(nullable = false)
    private String contrasena;
    @Basic
    @Column(nullable = false)
    private int telefono;
    
    @ManyToOne
    private Rol rol;

    @OneToOne
    private Carrito carrito;

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;

    public Usuario(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }


    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Venta> getVentas() {
        return this.ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    
    public Carrito getCarrito() {
        return this.carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

}

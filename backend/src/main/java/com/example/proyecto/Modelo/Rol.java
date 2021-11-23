package com.example.proyecto.Modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_roles")
    @SequenceGenerator(name = "seq_roles",initialValue = 2000)
    private Long id;

    @Basic
    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuario;


    public Rol() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuario() {
        return this.usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }
    
}

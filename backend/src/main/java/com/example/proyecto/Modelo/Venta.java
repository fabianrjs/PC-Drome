package com.example.proyecto.Modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_ventas")
    @SequenceGenerator(name = "seq_ventas",initialValue = 10000)
    private Long numeroOrden;

    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    private int precioTotal;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany
    private List<VentaDetalle> detallesVenta;


    public Venta() {
    }

    public Long getNumeroOrden() {
        return this.numeroOrden;
    }

    public void setNumeroOrden(Long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPrecioTotal() {
        return this.precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public List<VentaDetalle> getDetallesVenta() {
        return this.detallesVenta;
    }

    public void setDetallesVenta(List<VentaDetalle> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

}

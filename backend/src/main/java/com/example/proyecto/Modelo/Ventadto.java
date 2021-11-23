package com.example.proyecto.Modelo;

import java.util.HashMap;

public class Ventadto{
    
    private Long numeroOrden;
    private Long usuario;
    private int precioTotal;
    private String fecha;
    private HashMap<String,Integer> detallesVenta;


    public Ventadto() {
    }

    public Long getNumeroOrden() {
        return this.numeroOrden;
    }

    public void setNumeroOrden(Long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Long getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public int getPrecioTotal() {
        return this.precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public HashMap<String,Integer> getDetallesVenta() {
        return this.detallesVenta;
    }

    public void setDetallesVenta(HashMap<String,Integer> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    

}
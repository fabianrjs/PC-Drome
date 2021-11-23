package com.example.proyecto.Modelo;

public enum TipoProducto {
    cpu("Procesador"),
    ram("RAM"),
    gpu("Tarjeta Grafica"),
    disip("Disipador"),
    chasis("Chasis"),
    tarMadre("Tarjeta Madre"),
    discoDuro("Disco Duro"),
    fPoder("Fuente de Poder");

    private TipoProducto(String tipo) {
        this.tipo = tipo;
    }

    private String tipo;

    public String getTipo(){
        return this.tipo;
    }
}

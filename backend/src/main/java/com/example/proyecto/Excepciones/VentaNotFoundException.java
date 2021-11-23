package com.example.proyecto.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VentaNotFoundException extends RuntimeException{
    
    public VentaNotFoundException(Long id) {
		super("venta no encontrada: " + id);
	}
}

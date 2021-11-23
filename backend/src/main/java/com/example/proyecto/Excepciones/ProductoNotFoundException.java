package com.example.proyecto.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(Long id) {
		super("Producto no encontrado: " + id);
	}
}

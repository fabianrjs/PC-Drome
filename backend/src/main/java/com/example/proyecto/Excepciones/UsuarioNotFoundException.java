package com.example.proyecto.Excepciones;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{
    
    public UsuarioNotFoundException(Long id) {
		super("Cliente no encontrado: " + id);
	}

}

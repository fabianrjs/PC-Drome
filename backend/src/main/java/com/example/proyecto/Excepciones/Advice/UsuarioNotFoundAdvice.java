package com.example.proyecto.Excepciones.Advice;

import com.example.proyecto.Excepciones.UsuarioNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UsuarioNotFoundAdvice {
    
    @ResponseBody
	@ExceptionHandler(value = UsuarioNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String usuarioNotFoundHandler(UsuarioNotFoundException ex) {
		return ex.getMessage();
	}

}

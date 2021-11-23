package com.example.proyecto.Excepciones.Advice;

import com.example.proyecto.Excepciones.VentaNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class VentaNotFoundAdvice {
    
    @ResponseBody
	@ExceptionHandler(value = VentaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String usuarioNotFoundHandler(VentaNotFoundException ex) {
		return ex.getMessage();
	}

}

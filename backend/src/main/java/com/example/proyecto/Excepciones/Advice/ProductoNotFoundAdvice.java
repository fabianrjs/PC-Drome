package com.example.proyecto.Excepciones.Advice;

import com.example.proyecto.Excepciones.ProductoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ProductoNotFoundAdvice {
    
    @ResponseBody
	@ExceptionHandler(value = ProductoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String usuarioNotFoundHandler(ProductoNotFoundException ex) {
		return ex.getMessage();
	}
}

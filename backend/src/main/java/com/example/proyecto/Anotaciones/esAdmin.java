package com.example.proyecto.Anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.annotation.Secured;

@Target({ElementType.METHOD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Secured("ROLE_Admin")
public @interface esAdmin {}

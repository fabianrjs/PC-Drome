package com.example.proyecto.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.proyecto.Modelo.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario credenciales = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getCorreo(), credenciales.getContrasena(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		final String authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		final Map<String, Object> claims = new HashMap<>();
		claims.put("Authorities", authorities);
		
		String token = Jwts.builder()
				.setIssuedAt(new Date())
				.setIssuer("PC-Drome")
				.setClaims(claims)
				.setSubject(((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 Hora
				.signWith(SignatureAlgorithm.HS512, "12345")  // Algoritmo y clave secreta para firmar
				.compact();
		
		response.addHeader("Authorization", "Bearer "+ token);
	}
}


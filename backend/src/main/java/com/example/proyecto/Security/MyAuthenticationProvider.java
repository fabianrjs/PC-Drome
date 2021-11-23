package com.example.proyecto.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UsuarioRepo userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Optional<Usuario> uOptional = userRepository.buscarUsuarioPorCorreo(username);
        
        if (!uOptional.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
        Usuario usuario = uOptional.get();

        if (encoder.matches(password, usuario.getContrasena())){
        	
        	List<SimpleGrantedAuthority> authorities = getAuthorities(usuario);

    		// Crea el objeto principal
			org.springframework.security.core.userdetails.User principal = 
					new org.springframework.security.core.userdetails.User(
							usuario.getCorreo(), 
							usuario.getContrasena(), 
							authorities);
    				
        	return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private List<SimpleGrantedAuthority> getAuthorities(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().getNombre()));
		return authorities;
	}
}

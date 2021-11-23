package com.example.proyecto.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Repositorios.UsuarioRepo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
	private UsuarioRepo usuarioRepo;

	public UsuarioDetailsServiceImpl(UsuarioRepo usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> uOptional = usuarioRepo.buscarUsuarioPorCorreo(username);

		if (!uOptional.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
        Usuario usuario = uOptional.get();
		
		List<SimpleGrantedAuthority> roles = getRoles(usuario);

		return new org.springframework.security.core.userdetails.User
				(usuario.getCorreo(), usuario.getContrasena(), roles);
	}

	private List<SimpleGrantedAuthority> getRoles(Usuario usuario) {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().getNombre()));
		
		return roles;
	}
}


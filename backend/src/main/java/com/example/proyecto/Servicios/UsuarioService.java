package com.example.proyecto.Servicios;

import java.util.Optional;

import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Repositorios.UsuarioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepo usuarioRepo;

   
    public <S extends Usuario> S save(S entity) {
        return usuarioRepo.save(entity);
    }

    public Optional<Usuario> findById(Long id) {    
        return usuarioRepo.findById(id);
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorNombre(String nombre) {
        return usuarioRepo.buscarUsuarioPorNombre(nombre);
    }

    public Page<Usuario> getAllUsuarios(Pageable pageable) {
        return usuarioRepo.getAllUsuarios(pageable);
    }

    public void updateUsuario(Long id, String nombre, String correo, String contrasena, int telefono) {
        usuarioRepo.updatePerfilU(id, nombre, correo, contrasena, telefono);
    }
    
}

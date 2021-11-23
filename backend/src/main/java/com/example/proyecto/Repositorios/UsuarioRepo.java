package com.example.proyecto.Repositorios;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.proyecto.Modelo.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario,Long> {
    
    @Query(value = "SELECT u FROM Usuario u WHERE u.nombre = ?1")
    Optional<Usuario> buscarUsuarioPorNombre(String nombre);

    @Query(value = "SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> buscarUsuarioPorCorreo(String correo);

    @Query(value = "SELECT u FROM Usuario u")
    Page<Usuario> getAllUsuarios(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Usuario u SET u.nombre = :nombre, u.correo = :correo, u.contrasena = :contrasena, u.telefono = :telefono WHERE u.id = :id")
    void updatePerfilU(Long id, String nombre, String correo, String contrasena, int telefono);
}

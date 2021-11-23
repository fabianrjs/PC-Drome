package com.example.proyecto.rest;

import com.example.proyecto.Servicios.CarritoService;
import com.example.proyecto.Servicios.RolService;
import com.example.proyecto.Servicios.UsuarioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.proyecto.Anotaciones.esAdmin;
import com.example.proyecto.Anotaciones.esCliente;
import com.example.proyecto.Excepciones.UsuarioNotFoundException;
import com.example.proyecto.Modelo.Carrito;
import com.example.proyecto.Modelo.Rol;
import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Modelo.Usuariodto;


@RestController
@RequestMapping("usuarios")
public class UsuarioREST {

    private final Long ROLCLIENTE = 2001L;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @esAdmin
    public ResponseEntity<List<Usuariodto>> getUsuarios(@RequestParam(name = "page") int page,
    @RequestParam(name = "size", required = false, defaultValue = "5") int size){
        
       Page<Usuario> usuarios = usuarioService.getAllUsuarios(PageRequest.of(page, size));
       List<Usuariodto> res = convertirADto(usuarios); 

       return ResponseEntity.ok(res);
    }

    
    private List<Usuariodto> convertirADto(Page<Usuario> usuarios) {
        List<Usuariodto> res = new ArrayList<>();
        for(Usuario u : usuarios){
            res.add(mapper.map(u, Usuariodto.class));
        }
        return res;
    }

    @GetMapping("{id}")
    @Secured({"ROLE_Admin","ROLE_Cliente"})
    public ResponseEntity<Usuariodto> getUsuarioById(@PathVariable("id") Long pId){
        Optional<Usuario> u = usuarioService.findById(pId);
        if(u.isPresent()){
            return ResponseEntity.ok(mapper.map(u.get(), Usuariodto.class));
        }
        else{
            throw new UsuarioNotFoundException(pId);
        }
    }

    @GetMapping("/n/{nombre}")
    @esCliente
    public ResponseEntity<Usuariodto> getUsuarioByNombre(@PathVariable("nombre") String nombreU){
        Optional<Usuario> u = usuarioService.buscarUsuarioPorNombre(nombreU);
        if(u.isPresent()){
            return ResponseEntity.ok(mapper.map(u.get(), Usuariodto.class));
        }
        else{
            throw new UsuarioNotFoundException(u.get().getId()); 
        }  
    }

    @PostMapping("a")
    public void registrar(@RequestBody Usuariodto usuariodto){
        Usuario nUsuario = new Usuario();
        Carrito carrito = new Carrito();
        carritoService.save(carrito);
        nUsuario = mapper.map(usuariodto, Usuario.class);
        nUsuario.setContrasena(bCryptPasswordEncoder.encode(usuariodto.getContrasena()));
        Optional<Rol> rol = rolService.findById(ROLCLIENTE);
        if(rol.isPresent()){ 
          nUsuario.setRol(rol.get());  
        }  
        nUsuario.setCarrito(carrito);
        usuarioService.save(nUsuario);       
    }

    @PutMapping("u")
    @esCliente
    public ResponseEntity<String> updatePerfil(@RequestParam(name = "id", required = true) long id,
        @RequestBody Usuariodto usuariodto){
            if(usuarioService.findById(id).isPresent()){
                usuarioService.updateUsuario(id, usuariodto.getNombre(),
                                            usuariodto.getCorreo(),
                                            usuariodto.getContrasena(),
                                            usuariodto.getTelefono());
                return new ResponseEntity<>("perfil editado",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("usuario no encontrado",HttpStatus.NOT_FOUND);
            }
    }
}

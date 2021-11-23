package com.example.proyecto.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.proyecto.Anotaciones.esAdmin;
import com.example.proyecto.Anotaciones.esCliente;
import com.example.proyecto.Excepciones.ProductoNotFoundException;
import com.example.proyecto.Modelo.CarritoDetalle;
import com.example.proyecto.Modelo.Producto;
import com.example.proyecto.Modelo.Productodto;
import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Servicios.ProductoService;
import com.example.proyecto.Servicios.UsuarioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productos")
public class productoREST {
    
    @Autowired
	private UsuarioService uService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ModelMapper mapper;

    
    @GetMapping
    @Secured({"ROLE_Admin","ROLE_Cliente"})
	public ResponseEntity<List<Productodto>> getProductos(@RequestParam(name = "page") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size) {
		
		Page<Producto> productos = productoService.getProductos(PageRequest.of(page, size));	
		List<Productodto> res = convertirADto(productos);
		
		return ResponseEntity.ok(res);
	}

    private List<Productodto> convertirADto(Page<Producto> productos) {
        List<Productodto> res = new ArrayList<>();
        for(Producto p : productos){
            res.add(mapper.map(p, Productodto.class));
        }
        return res;
    }

    @GetMapping("tipo/{tipoProducto}")
    public ResponseEntity<List<Productodto>> getProductosByTipo(@PathVariable("tipoProducto") String tipo,
        @RequestParam(name = "page") int page,
        @RequestParam(name = "size", required = false, defaultValue = "100") int size){

        Page<Producto> productos = productoService.getProductos(PageRequest.of(page, size));	
		List<Productodto> allProductos = convertirADto(productos);
        List<Productodto> res = new ArrayList<>();

        for(Productodto producto : allProductos){
            if(producto.getTipoProducto().equals(tipo)){
                res.add(producto);
            }
        }
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("{id}")
    @esAdmin
    public ResponseEntity<Productodto> getProductoById(@PathVariable("id") Long pId){
        Optional<Producto> u = productoService.findById(pId);
        if(u.isPresent()){
            return ResponseEntity.ok(mapper.map(u.get(), Productodto.class));
        }
        else{
            throw new ProductoNotFoundException(pId);
        } 
    }

    
    @PostMapping("a")
    @esAdmin
    public ResponseEntity<Producto> addProducto(@RequestBody Productodto productodto){
         return ResponseEntity.ok(productoService.save(mapper.map(productodto, Producto.class)));
    }

    
    @DeleteMapping("d")
    @esAdmin
    public void deleteProducto(@RequestParam(name = "id", required = true) long id){
        if(productoService.findById(id).isPresent()){
            productoService.deleteById(id);
        }
    }

    
    @PutMapping("u")
    @esAdmin
    public void updateProducto(@RequestParam(name = "id", required = true) long id,
        @RequestBody Productodto productodto){
            if(productoService.findById(id).isPresent()){
                productoService.updateProductos(id, productodto.getNombre(),
                                                productodto.getPrecio(),
                                                productodto.getMarca(), 
                                                productodto.getDescripcion(),
                                                productodto.getTipoProducto(),
                                                productodto.getFoto());
            }
    }

    @GetMapping("productosCar/{nombre}")
	@esCliente
    public ResponseEntity<List<Productodto>> verProductosEnCarrito(@PathVariable("nombre") String nombreU){
        
        Optional<Usuario> uOpt = uService.buscarUsuarioPorNombre(nombreU);
        if(uOpt.isPresent()){
            List<Productodto> res = new ArrayList<>();
            Usuario usuario = uOpt.get();
            List<CarritoDetalle> prodEnCarrito = usuario.getCarrito().getProductos();
            for (CarritoDetalle carritoDetalle : prodEnCarrito) {
                res.add(mapper.map(carritoDetalle.getProducto(), Productodto.class));
            }
            return ResponseEntity.ok(res);
        }
        return null;
    }

}
package com.example.proyecto.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.proyecto.Anotaciones.esCliente;
import com.example.proyecto.Modelo.CarritoDetalle;
import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Modelo.Venta;
import com.example.proyecto.Modelo.VentaDetalle;
import com.example.proyecto.Servicios.CarritoDetalleService;
import com.example.proyecto.Servicios.CarritoService;
import com.example.proyecto.Servicios.ProductoService;
import com.example.proyecto.Servicios.UsuarioService;
import com.example.proyecto.Servicios.VdetalleService;
import com.example.proyecto.Servicios.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comprar")
public class comprarREST {
    
    @Autowired
    private VentaService ventaService;
	@Autowired
	private VdetalleService vService;
	@Autowired 
	private ProductoService pService;
	@Autowired
	private UsuarioService uService;
	@Autowired
	private CarritoService cService;
	@Autowired
	private CarritoDetalleService cDetalleService;

	@PostMapping("add")
	@esCliente
	public void agregarCarrito(@RequestParam(name = "nombre", required = true) String nombreU,
								@RequestParam(name = "prod", required = true) Long idProducto){
		boolean valido = true;
		Optional<Usuario> uOptional = uService.buscarUsuarioPorNombre(nombreU);
		if(uOptional.isPresent()){
			Usuario usuario = uOptional.get();
			if(pService.findById(idProducto).isPresent()){
				List<CarritoDetalle> prodEnCarrito = usuario.getCarrito().getProductos();
				for (CarritoDetalle carritoDetalle : prodEnCarrito) {
					if(carritoDetalle.getProducto().getId() == idProducto){
						valido = false;
					}
				}
				if(valido){
					CarritoDetalle carritoDetalle = new CarritoDetalle();
					carritoDetalle.setProducto(pService.findById(idProducto).get());
					carritoDetalle.setCantidad(1);
					cDetalleService.save(carritoDetalle);
				
					usuario.getCarrito().addProducto(carritoDetalle);
					cService.save(usuario.getCarrito());
				}		
			}				
		}
	}

	@PutMapping("incr")
	@esCliente
	public void actualizarCantidad(@RequestParam(name = "nombre", required = true) String nombreU,
									@RequestParam(name = "prod", required = true) long idproducto,
									@RequestParam(name = "cant",required = true) int cant){
		Optional<Usuario> uOptional = uService.buscarUsuarioPorNombre(nombreU);
		if(uOptional.isPresent()){
			Usuario usuario = uOptional.get();
			List<CarritoDetalle> prodEnCarrito = new ArrayList<>(usuario.getCarrito().getProductos()); 
			for (CarritoDetalle carritoDetalle : prodEnCarrito) {
				if(carritoDetalle.getProducto().getId() == idproducto){
					carritoDetalle.setCantidad(cant);
					cService.save(usuario.getCarrito());
				}
					
			}
		}		
	}

    @PostMapping("conf")
	@esCliente
    public void confirmarComprar(@RequestParam(name = "nombre", required = true) String nombreU){
		Optional<Usuario> uOptional = uService.buscarUsuarioPorNombre(nombreU);
		if(uOptional.isPresent()){
			Usuario usuario = uOptional.get();
			List<CarritoDetalle> productos = usuario.getCarrito().getProductos();
			if(productos.size() > 0){
				List<VentaDetalle> vdlista = new ArrayList<VentaDetalle>();
				int precioTotal = 0;
				Venta nVenta = new Venta();
				nVenta.setFecha(new Date());
				nVenta.setUsuario(usuario);

				for (CarritoDetalle carDe : productos) {
					precioTotal += carDe.getProducto().getPrecio();
					VentaDetalle vDetalle = new VentaDetalle();
					vDetalle.setProducto(carDe.getProducto());
					vDetalle.setCantidad(carDe.getCantidad());
					vService.save(vDetalle);
					vdlista.add(vDetalle);
				}
				nVenta.setPrecioTotal(precioTotal);
				nVenta.setDetallesVenta(vdlista);
				ventaService.save(nVenta);
				usuario.getCarrito().getProductos().clear();
				cService.save(usuario.getCarrito());
				cDetalleService.deleteAll();
			}
		}	
    }
}

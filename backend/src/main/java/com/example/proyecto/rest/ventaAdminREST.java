package com.example.proyecto.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.proyecto.Anotaciones.esAdmin;
import com.example.proyecto.Anotaciones.esCliente;
import com.example.proyecto.Excepciones.VentaNotFoundException;
import com.example.proyecto.Modelo.Venta;
import com.example.proyecto.Modelo.VentaDetalle;
import com.example.proyecto.Modelo.Ventadto;
import com.example.proyecto.Servicios.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reporte_ventas")
public class ventaAdminREST {
    
    @Autowired
    private VentaService ventaService;

    @GetMapping
    @esAdmin
	public ResponseEntity<List<Ventadto>> getVentas(@RequestParam(name = "page") int page,
			@RequestParam(name = "size", required = false, defaultValue = "1000") int size) {
		
		Page<Venta> ventas = ventaService.getVentas(PageRequest.of(page, size));
		List<Ventadto> res = convertirADto(ventas);
		
		return ResponseEntity.ok(res);
	}

    private List<Ventadto> convertirADto(Page<Venta> ventas) {
        List<Ventadto> res = new ArrayList<>();
        for(Venta v : ventas){
            Ventadto vdto = new Ventadto();
            vdto.setNumeroOrden(v.getNumeroOrden());
            vdto.setFecha(v.getFecha().toString());
            vdto.setPrecioTotal(v.getPrecioTotal());
            List<VentaDetalle> detalles = v.getDetallesVenta();
            HashMap<String,Integer> productos = new HashMap<String,Integer>();
            for (VentaDetalle vDetalle : detalles) {
                productos.put(vDetalle.getProducto().getNombre(), vDetalle.getCantidad());
            }
            vdto.setUsuario(v.getUsuario().getId());
            vdto.setDetallesVenta(productos);
            res.add(vdto);
        }
        return res;
    }

    @GetMapping("{id}")
    @Secured({"ROLE_Admin","ROLE_Cliente"})
    public ResponseEntity<Ventadto> getVentaById(@PathVariable("id") Long ventaId){

        Optional<Venta> venta = ventaService.findById(ventaId);
        if(venta.isPresent()){
            Ventadto vdto = new Ventadto();
            Venta v = venta.get();
            vdto.setNumeroOrden(v.getNumeroOrden());
            vdto.setFecha(v.getFecha().toString());
            vdto.setPrecioTotal(v.getPrecioTotal());
            List<VentaDetalle> detalles = v.getDetallesVenta();
            HashMap<String,Integer> productos = new HashMap<String,Integer>();
            for (VentaDetalle vDetalle : detalles) {
                productos.put(vDetalle.getProducto().getNombre(), vDetalle.getCantidad());
            }
            vdto.setDetallesVenta(productos);
            vdto.setUsuario(v.getUsuario().getId());
            return ResponseEntity.ok(vdto);
        }
        else{
            throw new VentaNotFoundException(ventaId);
        }
    }

}

package com.example.proyecto.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.proyecto.Anotaciones.esCliente;
import com.example.proyecto.Modelo.Venta;
import com.example.proyecto.Modelo.VentaDetalle;
import com.example.proyecto.Modelo.Ventadto;
import com.example.proyecto.Servicios.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("historial")
public class ventasClienteREST {
    
    @Autowired
    private VentaService ventaService;
	
    @GetMapping
    @esCliente
	public ResponseEntity<List<Ventadto>> getMisCompras(@RequestParam(name = "id")long id,
            @RequestParam(name = "page") int page,
			@RequestParam(name = "size", required = false, defaultValue = "100") int size
            )  {
		
		Page<Venta> ventas = ventaService.getVentasById(id,PageRequest.of(page, size));
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

	
}

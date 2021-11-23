import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Factura } from 'src/app/Modelo/Factura';
import { Producto } from 'src/app/Modelo/Producto';
import { Usuario } from 'src/app/Modelo/Usuario';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-detalles-compra',
  templateUrl: './detalles-compra.component.html',
  styleUrls: ['./detalles-compra.component.css']
})
export class DetallesCompraComponent implements OnInit {

  venta: Factura;
  productos: Producto[];
  productosCompletos: Producto[];
  usuario: Usuario;
  admin: boolean = true;

  constructor(private comprasS: ComprasService, public ruta: ActivatedRoute,
              public productos_s: ProductosService, private userS: UsuariosService) {
    this.venta = new Factura();
    this.productos = [];
    this.usuario = new Usuario();
    this.productosCompletos = [];
    //this.usuario = new Usuario("prueba", "prueba@hotmail.com", "soyUnaPrueba", 0, false);
  }

  ngOnInit(): void {
    this.cargar();
  }

  cargar(){
    this.ruta.params.subscribe(url => {
      let id = url['nOrden'];

      //obtener venta
      this.comprasS.getVenta(id).subscribe( v => {
        
        this.productos_s.getProductos().subscribe( p => {
          this.productosCompletos = p;
          //console.log(this.productosCompletos);
          for (let [key, name] of Object.entries(this.venta.detallesVenta)) {
            let p: Producto = new Producto();
            p.nombre = key;
            this.productosCompletos.forEach( e => {
              if( e.nombre === p.nombre ){
                p.foto = e.foto;
                p.precio = e.precio;
                p.marca = e.marca;
              }
            });
            this.productos.push(p);
          }  
        });

        this.venta = v;
          this.userS.getUsuarioById(this.venta.usuario).subscribe( u => {
            this.usuario = u;
          });
      })
    });
  }
}

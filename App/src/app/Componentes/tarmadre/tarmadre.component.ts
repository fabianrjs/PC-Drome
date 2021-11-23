import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-tarmadre',
  templateUrl: './tarmadre.component.html',
  styleUrls: ['./tarmadre.component.css']
})
export class TarmadreComponent implements OnInit {

  public listataMadre: Producto[];

  constructor(private servPro: ProductosService,
              private router: Router,
              private comprasS: ComprasService,
              private usuariosService: UsuariosService) {

    this.listataMadre = [];
   }

  ngOnInit(): void {
    this.servPro.getProductosTipo(TipoProducto.tarMadre).subscribe( e =>
      this.listataMadre = e
    );
  }

  public comprarProducto(madre: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(madre, nombre).subscribe( e => {
        console.log(madre.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }
}

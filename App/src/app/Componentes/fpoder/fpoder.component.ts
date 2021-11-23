import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-fpoder',
  templateUrl: './fpoder.component.html',
  styleUrls: ['./fpoder.component.css']
})
export class FpoderComponent implements OnInit {

  public listaFpoder: Producto[];

  constructor(private servPro: ProductosService,
              private router: Router,
              private comprasS: ComprasService,
              private usuariosService: UsuariosService) {

    this.listaFpoder = [];
  }

  ngOnInit(): void {
    this.servPro.getProductosTipo(TipoProducto.fPoder).subscribe( e =>
      this.listaFpoder = e
    );
  }

  comprarProducto(fpoder: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(fpoder, nombre).subscribe( e => {
        console.log(fpoder.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }

}

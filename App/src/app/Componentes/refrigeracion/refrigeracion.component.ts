import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-refrigeracion',
  templateUrl: './refrigeracion.component.html',
  styleUrls: ['./refrigeracion.component.css']
})
export class RefrigeracionComponent implements OnInit {

  public listaDis: Producto[];

  constructor(private servPro: ProductosService,
              private router: Router,
              private comprasS: ComprasService,
              private usuariosService: UsuariosService) {
    this.listaDis = [];
  }

  ngOnInit(): void {
    this.servPro.getProductosTipo(TipoProducto.disip).subscribe( e =>
      this.listaDis = e
    );
  }

  public comprarProducto(refri: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(refri, nombre).subscribe( e => {
        console.log(refri.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }
}

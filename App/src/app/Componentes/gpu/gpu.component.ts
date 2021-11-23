import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-gpu',
  templateUrl: './gpu.component.html',
  styleUrls: ['./gpu.component.css']
})
export class GpuComponent implements OnInit {

  public listaGPU: Producto[];

  constructor(private servPro: ProductosService,
              private router: Router,
              private comprasS: ComprasService,
              private usuariosService: UsuariosService) {

    this.listaGPU = [];
   }

  ngOnInit(): void {
    this.servPro.getProductosTipo(TipoProducto.gpu).subscribe( e =>
      this.listaGPU = e
    );
  }

  public comprarProducto(gpu: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(gpu, nombre).subscribe( e => {
        console.log(gpu.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }
}

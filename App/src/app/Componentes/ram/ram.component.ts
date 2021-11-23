import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-ram',
  templateUrl: './ram.component.html',
  styleUrls: ['./ram.component.css']
})
export class RamComponent implements OnInit {

  public listaRAM: Producto[];

  constructor(private servPro: ProductosService,
              private router: Router,
              private comprasS: ComprasService,
              private usuariosService: UsuariosService) {

    this.listaRAM = [];
   }

  ngOnInit(): void {
    this.servPro.getProductosTipo('RAM').subscribe( e =>
      this.listaRAM = e
    );
  }

  comprarProducto(ram: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(ram, nombre).subscribe( e => {
        console.log(ram.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }
}

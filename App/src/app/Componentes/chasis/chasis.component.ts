import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-chasis',
  templateUrl: './chasis.component.html',
  styleUrls: ['./chasis.component.css']
})
export class ChasisComponent implements OnInit {

  public listaChasis: Producto[];

  constructor(private servPro: ProductosService,private router: Router, private comprasS: ComprasService,
              private usuariosService: UsuariosService) {
    this.listaChasis = [];
  }

  ngOnInit(): void {
    this.servPro.getProductosTipo('Chasis').subscribe( e =>
      this.listaChasis = e
    );
  }
  
  comprarProducto(chasis: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(chasis, nombre).subscribe( e => {
        console.log(chasis.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-dduro',
  templateUrl: './dduro.component.html',
  styleUrls: ['./dduro.component.css']
})
export class DduroComponent implements OnInit {

  public listaDduro: Producto[];

  constructor(private servPro: ProductosService,
              private router: Router,
              private comprasS: ComprasService,
              private usuariosService: UsuariosService) {

    this.listaDduro = [];
  }

  ngOnInit(): void {
    this.servPro.getProductosTipo('Disco Duro').subscribe( e =>
      this.listaDduro = e
    );
  }

  public comprarProducto(dduro: Producto){
    if(this.usuariosService.getUsuarioActual().getNombreUsuario().length == 0){
      alert("Debe iniciar sesión para poder comprar un producto");
    }
    else{
      let nombre = this.usuariosService.getUsuarioActual().nombre;
      this.servPro.agregarProductoAlCarrito(dduro, nombre).subscribe( e => {
        console.log(dduro.id);
      });
      alert("Agregado al carrito exitosamente");
    }
  }

}

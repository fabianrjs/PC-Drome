import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ProductosService } from 'src/app/Servicios/Productos.service';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent implements OnInit {

  producto: Producto;

  constructor(public ruta: ActivatedRoute, public productos_s: ProductosService) 
  {
    this.producto = new Producto();
  }

  ngOnInit(): void {
    this.cargar();
  }

  cargar(){
    this.ruta.params.subscribe(url => {
      let id = url['id'];
      this.productos_s.getProducto( id ).subscribe(e => {
        this.producto = e;
        //console.log(this.producto);
      });
    });
  }

  public guardar(){
    console.log(this.producto);
    this.productos_s.modificarProducto(this.producto.id, this.producto).subscribe( e => {
      console.log(e);
    })
  }

}

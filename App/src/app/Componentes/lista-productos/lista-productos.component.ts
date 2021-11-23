import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/Modelo/Producto';
import { TipoProducto } from 'src/app/Modelo/TipoProducto';
import { ProductosService } from 'src/app/Servicios/Productos.service';

@Component({
  selector: 'app-lista-productos',
  templateUrl: './lista-productos.component.html',
  styleUrls: ['./lista-productos.component.css']
})
export class ListaProductosComponent implements OnInit {

  productos: Producto[];
  idProducto: number = 0;
  nombreProducto: string = "";

  constructor(public servPro: ProductosService) {
    this.productos = [];
  }

  ngOnInit(): void {
    this.servPro.getProductos().subscribe( e =>
      this.productos = e
    );
  }

  public darProducto(id: number, nombre: string){
    this.idProducto = id;
    this.nombreProducto = nombre;
  }

  public eliminar(): void{
    this.servPro.eliminarProducto(this.idProducto).subscribe( e => {
      this.servPro.getProductos().subscribe( e =>{
        this.productos = e;
      });
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Producto } from 'src/app/Modelo/Producto';
import { ProductosService } from 'src/app/Servicios/Productos.service';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrls: ['./agregar-producto.component.css']
})
export class AgregarProductoComponent implements OnInit {

  producto: Producto;

  ProductoForm = this.formB.group({
    Tipo: ['', Validators.required],
    Nombre: ['', Validators.required],
    Precio: ['', Validators.required],
    Marca: ['', Validators.required],
    Desc: ['', Validators.required]
  });

  constructor(public formB: FormBuilder,
              public productos_s: ProductosService) {

    this.producto = new Producto();
  }

  ngOnInit(): void {
  }

  public guardar(){
    /*console.log(this.ProductoForm.value.Tipo + ' - ' +
                this.ProductoForm.value.Nombre + ' - ' +
                this.ProductoForm.value.Precio + ' - ' +
                this.ProductoForm.value.Marca + ' - ' +
                this.ProductoForm.value.Desc);*/
    this.producto.nombre = this.ProductoForm.value.Nombre;
    this.producto.marca = this.ProductoForm.value.Marca;
    this.producto.precio = this.ProductoForm.value.Precio;
    this.producto.descripcion = this.ProductoForm.value.Desc;
    this.producto.tipoProducto = this.ProductoForm.value.Tipo;
    //this.producto.foto = 'fotoooo';

    this.productos_s.crearProducto(this.producto).subscribe( e =>{
      //console.log(e);
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  public listaProductos: Producto[];
  public vacio: boolean = true;
  public precioTotal: number = 0;

  cantProductos = this.formB.group({
    cantProd: this.formB.array([])
  });

  constructor(private prodServ: ProductosService,private userServ: UsuariosService,public formB: FormBuilder,
              private router: Router) {
    this.listaProductos = [];
   }

  ngOnInit(): void {
   const usuarioId = this.userServ.getUsuarioActual()
    this.prodServ.getProductosEnCarrito(usuarioId.getNombreUsuario()).subscribe(resultado =>{
      this.listaProductos = resultado;
      if(this.listaProductos.length > 0){
        this.vacio = false;
        document.getElementById("empty")!.style.display = "none";
      }
      this.listaProductos.forEach( p =>{
        this.precioTotal += p.precio;
      })
    })
  }
  
  public pagar():void{
    if(this.precioTotal == 0){
      alert("Debe agregar productos primero");
    }else{
      this.prodServ.compraPendiente.precioTotal = this.precioTotal;
      this.prodServ.compraPendiente.productos = this.listaProductos;
      this.router.navigate(['/infoTarjeta']);
    }
  }
}

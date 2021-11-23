import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Producto } from 'src/app/Modelo/Producto';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { ProductosService } from 'src/app/Servicios/Productos.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-info-pago',
  templateUrl: './info-pago.component.html',
  styleUrls: ['./info-pago.component.css']
})
export class InfoPagoComponent implements OnInit {

  prodsAPagar: Producto[];
  totalAPagar: number;

  payForm = this.formB.group({
    direc: ['',Validators.required],
    tarjeta: ['',Validators.required],
    nombreP: ['',Validators.required],
    ven: ['',Validators.required],
    cvv: ['',Validators.required]
  });

  constructor(public formB: FormBuilder,private compras: ComprasService,private router: Router,
              private prodServ: ProductosService, private userServ: UsuariosService) {
    this.prodsAPagar = prodServ.compraPendiente.productos;
    this.totalAPagar = prodServ.compraPendiente.precioTotal;
   }

  ngOnInit(): void {
  }

  confirmarPago(){
    this.prodServ.comprar(this.userServ.getUsuarioActual().nombre, this.prodsAPagar).subscribe( e =>{
      console.log("Compra realizada");
      alert("Compra realizada exitosamente");
    });
    this.compras.crearFactura();
    this.router.navigate(['./']);
  }

}

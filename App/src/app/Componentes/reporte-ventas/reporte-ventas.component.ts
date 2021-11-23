import { Component, OnInit } from '@angular/core';
import { Factura } from 'src/app/Modelo/Factura';
import { ComprasService } from 'src/app/Servicios/compras.service';

@Component({
  selector: 'app-reporte-ventas',
  templateUrl: './reporte-ventas.component.html',
  styleUrls: ['./reporte-ventas.component.css']
})
export class ReporteVentasComponent implements OnInit {

  ventas: Factura[];

  constructor(public compras_s: ComprasService) {
    this.ventas = [];
  }

  ngOnInit(): void {
    this.compras_s.getVentas().subscribe(e =>{
      this.ventas = e;
    })
  }

}

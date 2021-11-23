import { Component, OnInit } from '@angular/core';
import { Factura } from 'src/app/Modelo/Factura';
import { Usuario } from 'src/app/Modelo/Usuario';
import { ComprasService } from 'src/app/Servicios/compras.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-historial-compras',
  templateUrl: './historial-compras.component.html',
  styleUrls: ['./historial-compras.component.css']
})
export class HistorialComprasComponent implements OnInit {

  listaCompras: Array<Factura>;
  constructor(private comprasS: ComprasService, private userS: UsuariosService) {
      this.listaCompras = new Array<Factura>(); 
   }

  ngOnInit(): void {
    this.userS.getUsuarioByNombre(this.userS.getUsuarioActual().nombre).subscribe( u => {
      console.log(u);
      let user: Usuario = u;
      this.comprasS.getCompras(u.id).subscribe( c => {
        this.listaCompras = c;
      });
    });
  }

}

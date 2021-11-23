import { Component, OnInit } from '@angular/core';
import {Router, NavigationEnd} from "@angular/router";
import { TokenServiceService } from 'src/app/Servicios/token-service.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  usuario: boolean = false;
  admin: boolean = false;

  constructor( public router: Router,
               public user_s: UsuariosService, public tokenServ: TokenServiceService ) {
    this.router.events.subscribe( ev => {
      if (ev instanceof NavigationEnd) { this.verificar(); }
    });
   }

  ngOnInit(): void {
  }

  public verificar(){
    /*console.log('<---');
    this.user_s.imprimirUsuarios();
    console.log('--->');*/
    if( this.user_s.getUsuarioActual().getCorreo() === "" ){
      this.usuario = false;
    }else{
      //console.log('Usuario Actual' + this.user_s.getUsuarioActual().toString());
      this.usuario = true;
      this.admin = this.user_s.getUsuarioActual().isAdmin();
    }
  }

  public cerrar_sesion(){
    this.user_s.cerrar_sesion();
    if(this.router.getCurrentNavigation() == null){
      this.router.navigate(['./iniciar_sesion']);
      
    }
    this.tokenServ.cerrarSesion();
  }

}

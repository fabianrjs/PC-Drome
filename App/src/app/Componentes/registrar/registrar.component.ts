import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { TokenServiceService } from 'src/app/Servicios/token-service.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {

  registerForm = this.formB.group({
      usuario: ['', Validators.required],
      correo: ['', Validators.compose([Validators.email, Validators.required])],
      telefono: [ ],
      contrasena1: ['', Validators.required],
      contrasena2: ['', Validators.required]
    });

  constructor(public formB: FormBuilder,
              public user_s: UsuariosService,
              public router: Router,
              public tokenStorage: TokenServiceService) { }


  ngOnInit(): void {
    this.tokenStorage.getToken();
  }

  public async registrar(){
    console.log(
      'Usuario: ' + this.registerForm.value.usuario +
      '\nCorreo: ' + this.registerForm.value.correo +
      '\nContraseña 1: ' + this.registerForm.value.contrasena1 +
      '\nContraseña 2: ' + this.registerForm.value.contrasena2 +
      '\nTelefono: ' + this.registerForm.value.telefono
    );
    if(this.registerForm.value.contrasena1 == this.registerForm.value.contrasena2){
      this.user_s.crear_usuario(this.registerForm.value.usuario,
                                this.registerForm.value.correo,
                                this.registerForm.value.contrasena1,
                                this.registerForm.value.telefono, false);
      this.user_s.registrar(this.registerForm.value.correo,this.registerForm.value.contrasena1
                            ,this.registerForm.value.usuario,this.registerForm.value.telefono).subscribe();
      
      this.user_s.iniciar_sesion(this.registerForm.value.correo, this.registerForm.value.contrasena1);
      await this.delay(500);
      this.user_s.login(this.registerForm.value.correo,this.registerForm.value.contrasena1).subscribe((data: HttpResponse<any>) => {
        console.log(data.headers.get('Authorization')!);   
        this.tokenStorage.saveToken(data.headers.get('Authorization')!); 
      })
      this.router.navigate(['/']);
    }else{
      alert('Las contraseñas ingresadas son diferentes');
    }
  }

  private delay(ms: number){
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

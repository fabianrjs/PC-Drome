import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenServiceService } from 'src/app/Servicios/token-service.service';
import { UsuariosService } from 'src/app/Servicios/usuarios.service';
import { JwtServiceService } from 'src/app/Servicios/jwt-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.formB.group({
    correo: ['', Validators.compose([Validators.email, Validators.required])],
    contrasena: ['', Validators.required]
  });

  constructor(public user_s: UsuariosService,
              public formB: FormBuilder,
              public router: Router,
              public tokenStorage: TokenServiceService,
              public jwtService: JwtServiceService) { }

  ngOnInit(): void {
    
    this.tokenStorage.getToken();
  }

  public iniciar_sesion(){
    
    
    if(this.user_s.iniciar_sesion(this.loginForm.value.correo, this.loginForm.value.contrasena)){
      this.user_s.login(this.loginForm.value.correo, this.loginForm.value.contrasena).subscribe((data: HttpResponse<any>) => {   
        this.tokenStorage.saveToken(data.headers.get('Authorization')!); 
      });
      this.router.navigate(['./']);
    }else{
      document.getElementById("mensaje")!.innerHTML = "Correo o contraseña incorrectos";
      document.getElementById("mensaje")!.style.margin = "5px";
      document.getElementById("login")!.style.border = "solid 2px red";
      document.getElementById("password")!.style.border = "solid 2px red";
    }
    
  }

}

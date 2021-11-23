import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../Modelo/Usuario';
import { usuarioAuth } from '../Modelo/usuarioAuth';
import { TokenServiceService } from './token-service.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  observe: 'response' as 'response'
};

@Injectable({
  providedIn: 'root'
})

export class UsuariosService {
  
  private url: string = "http://localhost:8090";
  private usuarios: Array<Usuario>;
  private usuarioActual: Usuario;

  constructor(private http: HttpClient, private tokenStorage: TokenServiceService) {
    this.usuarios = new Array<Usuario>();
    this.usuarioActual = new Usuario("", "", "", 0, false);
    //this.usuarioActual = new Usuario("Administrador", "admin@gmail.com", "admin", "", true);
    this.crear_admin();
  }

  headers = new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Authorization', this.tokenStorage.getToken());

  public crear_admin(){
    this.usuarios.push( new Usuario("Administrador", "admin@gmail.com", "admin", 0, true) );
    this.usuarios.push( new Usuario("prueba", "prueba@hotmail.com", "123456", 0, false) );
    this.usuarios.push( new Usuario("julian", "julian@gmail.com", "123456", 0, false) );
  }

  public crear_usuario(usuario: string, correo: string, contrasena: string, telefono: number, admin: boolean){
    let nUsuario = new Usuario(usuario, correo, contrasena, telefono, false) 
    this.usuarios.push(nUsuario);
  }

  public iniciar_sesion(correo: string, contrasena: string){
    let valido: boolean = false;
    //console.log('Correo: ' + correo + 'Contraseña: ' + contrasena);
    this.usuarios.forEach( e => {
      if( e.getCorreo() === correo && e.getContrasena() === contrasena ){
        this.usuarioActual = e;
        valido = true;
        return;
      }
    });
    return valido;
  }

  public cerrar_sesion(){
    this.usuarioActual = new Usuario("", "", "", 0, false);
  }

  public getUsuarioActual(){
    return this.usuarioActual;
  }

  public buscar_correo(correo: string){
    let encontrado: boolean = false;
    this.usuarios.forEach( e => {
      if( correo === e.getCorreo()){
        encontrado = true;
        return;
      }
    })
    return encontrado;
  }

  public buscar_contrasena(contrasena: string){
    let encontrado: boolean = false;
    this.usuarios.forEach( e => {
      if( contrasena === e.getContrasena()){
        encontrado = true;
        return;
      }
    })
    return encontrado;
  }

  public imprimirUsuarios(){
    this.usuarios.forEach( e => console.log(e.toString()))
  }

  public buscarNombreUsuario(correo: string){
    let nombre: string = "";
    this.usuarios.forEach( e => {
      if( correo === e.getCorreo()){
        nombre = e.getNombreUsuario();
      }
    })
    return nombre;
  }

  public login(correo: string, contrasena: string):Observable<any>{
    let auth: usuarioAuth = new usuarioAuth(correo,contrasena)
    return this.http.post(this.url + "/login",auth,httpOptions);
  }

  public registrar(correo: string, contrasena: string, nombre: string, telefono: number):Observable<Usuario>{
    let nUsuario = new Usuario(nombre,correo,contrasena,telefono)
    return this.http.post<Usuario>(this.url + "/usuarios/a",nUsuario);
  }

  public getUsuarioByNombre(nombre: string):Observable<Usuario>{
    return this.http.get<Usuario>(this.url + "/usuarios/n/" + nombre, {headers: this.headers});
  }

  public getUsuarioById(id: string): Observable<Usuario>{
    return this.http.get<Usuario>(this.url + '/usuarios/' + id, {headers: this.headers});
  }

}

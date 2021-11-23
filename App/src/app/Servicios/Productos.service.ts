import { Injectable } from '@angular/core';
import { Producto } from '../Modelo/Producto';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenServiceService } from './token-service.service';
import { Factura } from '../Modelo/Factura';



@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private url: string = "http://localhost:8090/productos";
  private url2: string = "http://localhost:8090/comprar";

  public compraPendiente: Factura;

  constructor(private http: HttpClient,public tokenStorage: TokenServiceService) {
    this.compraPendiente = new Factura();
  }

  headers = new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Authorization', this.tokenStorage.getToken());

  public getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + '?page=0&size=100',{headers: this.headers});
  }

  public getProductosTipo( tipo: string ): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + '/tipo/' + tipo + '?page=0');
  }

  public getProducto( id: string ): Observable<Producto>{
    return this.http.get<Producto>(this.url + '/' + id,{headers: this.headers});
  }

  public eliminarProducto( id: number): Observable<Producto>{
    return this.http.delete<Producto>(this.url + '/d?id=' + id,{headers: this.headers});
  }

  public modificarProducto( id: number, p: Producto): Observable<Producto>{
    return this.http.put<Producto>(this.url + '/u?id=' + id, p,{headers: this.headers});
  }

  public crearProducto( p: Producto ): Observable<Producto>{
    return this.http.post<Producto>(this.url + '/a', p,{headers: this.headers});
  }

  public getProductosEnCarrito(nombre: string): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + '/productosCar/' + nombre,{headers: this.headers})
  }

  public agregarProductoAlCarrito(p: Producto, nombre: string): Observable<Producto>{
    return this.http.post<Producto>(this.url2 + '/add?nombre=' + nombre + '&prod=' + p.id, p, {headers: this.headers});
  }

  public comprar(nombre: string, p: Producto[]): Observable<Producto>{
    return this.http.post<Producto>( this.url2 + '/conf?nombre=' + nombre, p, {headers: this.headers});
  }

}

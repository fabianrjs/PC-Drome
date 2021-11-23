import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Factura } from '../Modelo/Factura';
import { TokenServiceService } from './token-service.service';

@Injectable({
  providedIn: 'root'
})
export class ComprasService {

  private url: string = 'http://localhost:8090/reporte_ventas'
  private url2: string = "http://localhost:8090/historial"

  constructor(private http: HttpClient,public tokenStorage: TokenServiceService) {
  }
  
  headers = new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Authorization', this.tokenStorage.getToken());

  public crearFactura(){
    
  }

  public getCompras(id: string): Observable<Factura[]>{
    return this.http.get<Factura[]>(this.url2 + '?id=' + id + '&page=0&size=100', {headers: this.headers});
  }

  public getVentas(): Observable<Factura[]>{
    return this.http.get<Factura[]>(this.url + '?page=0&size=100',{headers: this.headers});
  }

  public getVenta(id: number): Observable<Factura>{
    return this.http.get<Factura>(this.url + '/' + id,{headers: this.headers});
  }
}

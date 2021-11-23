import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({
  providedIn: 'root'
})
export class JwtServiceService {

  helper = new JwtHelperService();

  constructor() { }

  decodeToken(token: string): void {
   var codToken = token.split(" ",2)
   const decodToken = this.helper.decodeToken(codToken[1])
   console.log(decodToken)
  }
}

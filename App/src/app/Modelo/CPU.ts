
import {Producto} from "./Producto";

export class CPU extends Producto{
  private modelo: string;
  private velocidad: string;
  private nNucleos: string;
  private tamCache: string;
  private generacion: string;
  constructor(nombre: string, idProducto: number, precio: number, marca: string,
              descripcion: string, foto: string, modelo: string,
              velocidad: string, nNucleos: string, tamCache: string,
              generacion: string
              ) {
    super(nombre, idProducto, precio, marca, descripcion, foto);
    this.modelo = modelo;
    this.velocidad = velocidad;
    this.nNucleos = nNucleos;
    this.tamCache = tamCache;
    this.generacion = generacion;
  }
}

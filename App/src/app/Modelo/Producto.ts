import { TipoProducto } from "./TipoProducto";

export class Producto{
  nombre: string;
  id: number;
  precio: number;
  marca: string;
  descripcion: string;
  foto: string;
  tipoProducto: TipoProducto

  constructor (nombre?: any, idProducto?: any, precio?: any, marca?: any, descripcion?: any, foto?: any, tipo?: any) {
    this.nombre = nombre;
    this.id = idProducto;
    this.precio = precio;
    this.marca = marca;
    this.descripcion = descripcion;
    this.foto = foto;
    this.tipoProducto = tipo;
  }

  public darPrecio(): number{
    return this.precio;
  }

  public darNombre(): string{
    return this.nombre;
  }

  public darIdProducto(): number{
    return this.id;
  }

  public darFoto(): string{
    return this.foto;
  }

  public darMarca(): string{
    return this.marca;
  }

  public darDesc(): string{
    return this.descripcion;
  }

  public darTipo(): TipoProducto{
    return this.tipoProducto;
  }
}

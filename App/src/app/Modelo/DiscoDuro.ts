import {Producto} from './Producto';
export class DiscoDuro extends Producto{
    private capacidad: string;
    private tipoDisco: string;

    constructor(nombre: string, idProducto: number, precio: number, marca: string,
        descripcion: string, foto: string, capacidad: string,
        tipo: string){
            super(nombre, idProducto, precio, marca, descripcion, foto);
            this.capacidad = capacidad;
            this.tipoDisco = tipo;
    }
}
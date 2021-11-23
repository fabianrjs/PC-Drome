import {Producto} from './Producto';
export class RAM extends Producto{
    private tamMemoria: string;
    private velocidad: string;

    constructor(nombre: string, idProducto: number, precio: number, marca: string,
        descripcion: string, foto: string, tamMemoria: string, velocidad: string){

        super(nombre, idProducto, precio, marca, descripcion, foto);
        this.tamMemoria = tamMemoria;
        this.velocidad = velocidad;
    }
}

import {Producto} from './Producto';

export class Disipador extends Producto{
    private socketCPU: string;
    private tipoRefri: string;

    constructor(nombre: string, idProducto: number, precio: number, marca: string,
        descripcion: string, foto: string, socketCPU: string,
        tipoRefri: string){
            super(nombre, idProducto, precio, marca, descripcion, foto);
            this.socketCPU = socketCPU;
            this.tipoRefri = tipoRefri;
            }
}
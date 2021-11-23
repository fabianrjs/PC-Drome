import {Producto} from './Producto';
export class Motherboard extends Producto{
    private ranurasRAM: number;
    private tecnologiaRAM: string;
    private tipoSocket: string;
    private modelo: string;

    constructor(nombre: string, idProducto: number, precio: number,
         marca: string,descripcion: string, foto: string, ranurasRAM: 
         number,tecnologiaRAM: string, tipoSocket: string, modelo: string){
        super(nombre, idProducto, precio, marca, descripcion, foto);
        this.ranurasRAM = ranurasRAM;
        this.tecnologiaRAM = tecnologiaRAM;
        this.tipoSocket = tipoSocket;
        this.modelo = modelo;
    }

}
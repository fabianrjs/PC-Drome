import {Producto} from './Producto';
export class GPU extends Producto{

    private almacenamiento: string;
    private tipoRAM: string;

    constructor(nombre: string, idProducto: number, precio: number, marca: string,
                descripcion: string, foto: string, almacenamiento: string,
                 tipoRAM: string){
        super(nombre, idProducto, precio, marca, descripcion, foto);
        this.almacenamiento = almacenamiento;
        this.tipoRAM = tipoRAM;
    }
}
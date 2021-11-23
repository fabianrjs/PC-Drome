import {Producto} from './Producto';

export class Chasis extends Producto{
    private compMother: string;

    constructor(nombre: string, idProducto: number, precio: number, marca: string,
                descripcion: string, foto: string, compMother: string){

        super(nombre, idProducto, precio, marca, descripcion, foto);
        this.compMother = compMother;
        }
}

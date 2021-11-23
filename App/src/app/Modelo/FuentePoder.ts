import {Producto} from './Producto';
export class FuentePoder extends Producto{
    private certificado: string;
    private potencia: string;
    private tipoFuente: string;

    constructor(nombre: string, idProducto: number, precio: number, marca: string,
        descripcion: string, foto: string, certificado: string,
        potencia: string, tipo: string){

        super(nombre, idProducto, precio, marca, descripcion, foto);
        this.certificado = certificado;
        this.potencia = potencia;
        this.tipoFuente = tipo;
    }
}
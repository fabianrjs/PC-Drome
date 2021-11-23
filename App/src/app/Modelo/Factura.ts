import { Producto } from "./Producto";

export class Factura{
    numeroOrden: any;
    usuario: any;
    producto: any;
    precioTotal: number;
    fecha: any;
    productos: Producto[];
    detallesVenta: any;

    constructor(numeroOrden?: number, fecha?: string, correoUsuario?: string, producto?: number){
        this.numeroOrden = numeroOrden;
        this.producto = producto;
        this.precioTotal = 0;
        this.fecha = fecha;
        this.usuario = correoUsuario;
        this.productos = new Array();
    }

    darOrden(): number{
        return this.numeroOrden;
    }

    darCorreo(): string{
        return this.usuario;
    }

    darProducto(): number{
        return this.producto;
    }

    darPrecio(): number{
        return this.precioTotal;
    }

    darFecha(): string{
        return this.fecha;
    }

    calcularPrecio(){
        this.precioTotal = this.producto.darPrecio();
    }


}



export class Usuario{
    nombre: any;
    correo: any;
    contrasena: any;
    telefono: any;
    admin: any;
    id: string;

    constructor(nombre?: string, correo?: string, contrasena?: string, telefono?: number, admin?: boolean){
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.admin = admin;
        this.id = '';
    }

    public getCorreo() {
        return this.correo;
    }

    public getContrasena(){
        return this.contrasena;
    }

    public getNombreUsuario(){
        return this.nombre;
    }

    public toString(){
        return ('\nUsuario: ' + this.nombre +
                '\nCorreo: ' + this.correo +
                '\nContraseña: ' + this.contrasena +
                '\nTelefono: ' + this.telefono);
    }

    public isAdmin(){
        return this.admin;
    }
}
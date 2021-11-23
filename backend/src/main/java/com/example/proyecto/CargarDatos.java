package com.example.proyecto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.proyecto.Modelo.Carrito;
import com.example.proyecto.Modelo.Producto;
import com.example.proyecto.Modelo.Rol;
import com.example.proyecto.Modelo.TipoProducto;
import com.example.proyecto.Modelo.Usuario;
import com.example.proyecto.Modelo.Venta;
import com.example.proyecto.Modelo.VentaDetalle;
import com.example.proyecto.Servicios.CarritoService;
import com.example.proyecto.Servicios.ProductoService;
import com.example.proyecto.Servicios.RolService;
import com.example.proyecto.Servicios.UsuarioService;
import com.example.proyecto.Servicios.VdetalleService;
import com.example.proyecto.Servicios.VentaService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
class CargarDatos {
    
    @Bean
    CommandLineRunner inicializar(ProductoService prodServ, UsuarioService userv, 
                                    VentaService ventaserv, RolService rolService,
                                    CarritoService carritoService, VdetalleService vdetalleService,
                                    BCryptPasswordEncoder bCryptPasswordEncoder
                                    ){
        return args->{
            System.out.println("backend iniciado");
            iniUsuarios(userv,rolService,carritoService,bCryptPasswordEncoder);
            inichasis(prodServ);
            iniCpus(prodServ);
            iniDiscos(prodServ);
            iniDisipadores(prodServ);
            iniFuentes(prodServ);
            iniGpu(prodServ);
            iniMotherBoards(prodServ);
            iniRam(prodServ);
            iniVentas(ventaserv,vdetalleService,bCryptPasswordEncoder);
        };
    }

    public void iniUsuarios(UsuarioService userv,RolService rolService, CarritoService carritoService,BCryptPasswordEncoder bCryptPasswordEncoder){
        Rol nRol = new Rol();
        nRol.setNombre("Admin");
        rolService.save(nRol);

        Rol nRol2 = new Rol();
        nRol2.setNombre("Cliente");
        rolService.save(nRol2);

        Carrito carrito1 = new Carrito();
        Carrito carrito2 = new Carrito();
        carritoService.save(carrito1);
        carritoService.save(carrito2);

        Usuario u1 = new Usuario();
        u1.setNombre("Administrador");
        u1.setCorreo("admin@gmail.com");
        u1.setContrasena(bCryptPasswordEncoder.encode("admin"));
        u1.setTelefono(333333333);
        u1.setRol(nRol);
        u1.setCarrito(carrito1);
        userv.save(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("prueba");
        u2.setCorreo("prueba@hotmail.com");
        u2.setContrasena(bCryptPasswordEncoder.encode("123456"));
        u2.setTelefono(12345678);
        u2.setRol(nRol2);
        u2.setCarrito(carrito2);
        userv.save(u2);

    }

    public void iniCpus(ProductoService prodServ){
        Producto c1 = new Producto();
        c1.setNombre("INTEL CORE I5 9400F");
        c1.setDescripcion("Procesador de 9a generación con 6 nucleos, una velocidad de 2.9GHz y 8Mb de cache");
        c1.setMarca("Intel");
        c1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/PROCESADORES%2F1.jpg?alt=media&token=c210100b-a614-4953-9e76-7d2cfc93cb8c");
        c1.setPrecio(650000);
        c1.setTipoProducto(TipoProducto.cpu.getTipo());
        prodServ.save(c1);

        Producto c2 = new Producto();
        c2.setNombre("INTEL CORE I7 10700");
        c2.setDescripcion("Procesador de 10a generación con 8 nucleos, una velocidad de 2.9GHz y 16Mb de cache");
        c2.setMarca("Intel");
        c2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/PROCESADORES%2F10.jpg?alt=media&token=518db160-f966-4e37-869e-26da2f57b448");
        c2.setPrecio(1450000);
        c2.setTipoProducto(TipoProducto.cpu.getTipo());
        prodServ.save(c2);

        Producto c3 = new Producto();
        c3.setNombre("INTEL CORE I7 10700K");
        c3.setDescripcion("Procesador de 10a generación con 8 nucleos, una velocidad de 3.8GHz y 16Mb de cache");
        c3.setMarca("Intel");
        c3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/PROCESADORES%2F11.jpg?alt=media&token=4d7ea4d9-4aed-43f0-90d0-34ca5c74bab1");
        c3.setPrecio(1560000);
        c3.setTipoProducto(TipoProducto.cpu.getTipo());
        prodServ.save(c3);

        Producto c4 = new Producto();
        c4.setNombre("INTEL CORE I9 10900");
        c4.setDescripcion("Procesador de 10a generación con 10 nucleos, una velocidad de 2.9GHz y 24Mb de cache");
        c4.setMarca("Intel");
        c4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/PROCESADORES%2F12.jpg?alt=media&token=3bde6e56-5de2-46c1-868f-7ced6a76facc");
        c4.setPrecio(1990000);
        c4.setTipoProducto(TipoProducto.cpu.getTipo());
        prodServ.save(c4);

        Producto c5 = new Producto();
        c5.setNombre("AMD RYZEN R5 3600");
        c5.setDescripcion("Procesador R5 con 6 nucleos, una velocidad de 3.6GHz y 16Mb de cache");
        c5.setMarca("AMD");
        c5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/PROCESADORES%2F15.jpg?alt=media&token=8c5d67b7-6c53-4924-901a-f93f7599e494");
        c5.setPrecio(920000);
        c5.setTipoProducto(TipoProducto.cpu.getTipo());
        prodServ.save(c5);
    }

    public void inichasis(ProductoService prodServ){
        Producto ch1 = new Producto();
        ch1.setNombre("AEROCOOL STREAK");
        ch1.setDescripcion("Chasis tipo Mid tower, compatible con tarjetas madre ATX");
        ch1.setMarca("AEROCOOL");
        ch1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/CHASIS%2F100.jpg?alt=media&token=8135e042-1c1d-45b2-8ba1-f58785d40016");
        ch1.setPrecio(229000);
        ch1.setTipoProducto(TipoProducto.chasis.getTipo());
        prodServ.save(ch1);

        Producto ch2 = new Producto();
        ch2.setNombre("MSI MPG");
        ch2.setDescripcion("Chasis tipo torre completo, compatible con tarjetas madre ATX");
        ch2.setMarca("MSI");
        ch2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/CHASIS%2F109.jpg?alt=media&token=5227368a-a3fe-4eb2-82e6-9fd823790bea");
        ch2.setPrecio(549000);
        ch2.setTipoProducto(TipoProducto.chasis.getTipo());
        prodServ.save(ch2);

        Producto ch3 = new Producto();
        ch3.setNombre("THERMALTAKE LEVEL 20");
        ch3.setDescripcion("Chasis tipo Mid tower, compatible con tarjetas madre ATX");
        ch3.setMarca("THERMALTAKE");
        ch3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/CHASIS%2F110.jpg?alt=media&token=877dc8d7-b49f-40ae-9f7f-4aef37bc4686");
        ch3.setPrecio(559000);
        ch3.setTipoProducto(TipoProducto.chasis.getTipo());
        prodServ.save(ch3);

        Producto ch4 = new Producto();
        ch4.setNombre("AEROCOOL PRIME");
        ch4.setDescripcion("Chasis tipo Mid tower, compatible con tarjetas madre ATX");
        ch4.setMarca("AEROCOOL");
        ch4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/CHASIS%2F101.jpg?alt=media&token=1814d9d1-d539-46c1-9121-f7e9997c5cfb");
        ch4.setPrecio(245000);
        ch4.setTipoProducto(TipoProducto.chasis.getTipo());
        prodServ.save(ch4);

        Producto ch5 = new Producto();
        ch5.setNombre("AEROCOOL RIFT");
        ch5.setDescripcion("Chasis tipo Mid tower, compatible con tarjetas madre ATX");
        ch5.setMarca("AEROCOOL");
        ch5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/CHASIS%2F102.jpg?alt=media&token=97454249-1985-481a-8e64-2f81ed51a84a");
        ch5.setPrecio(255000);
        ch5.setTipoProducto(TipoProducto.chasis.getTipo());
        prodServ.save(ch5);
    }
 
    public void iniRam(ProductoService prodServ){
        Producto r1 = new Producto();
        r1.setNombre("DDR4 8GB 2600MHZ");
        r1.setDescripcion("Memoria ram de 8GB y 2600 MHz de velocidad");
        r1.setMarca("Crucial value");
        r1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/RAM%2F61.jpg?alt=media&token=dcd85624-2817-4b4e-b55b-ac2fa3fff9ec");
        r1.setPrecio(165000);
        r1.setTipoProducto(TipoProducto.ram.getTipo());
        prodServ.save(r1);

        Producto r2 = new Producto();
        r2.setNombre("DDR4 16 GB 3000 MHZ");
        r2.setDescripcion("Memoria ram de 16GB y 3000 MHz de velocidad");
        r2.setMarca("XPG Spectrix D60");
        r2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/RAM%2Fxpgd60.jpg?alt=media&token=04039eb5-10fa-4afc-86b1-ae0fa421658c");
        r2.setPrecio(335000);
        r2.setTipoProducto(TipoProducto.ram.getTipo());
        prodServ.save(r2);

        Producto r3 = new Producto();
        r3.setNombre("DDR4 16 GB 3200 MHZ");
        r3.setDescripcion("Memoria ram de 16GB y 3200 MHz de velocidad");
        r3.setMarca("Kingston Hyper-X FURY");
        r3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/RAM%2F80.jpg?alt=media&token=98ec8ff2-9870-4429-b15d-5045fa02838d");
        r3.setPrecio(375000);
        r3.setTipoProducto(TipoProducto.ram.getTipo());
        prodServ.save(r3);

        Producto r4 = new Producto();
        r4.setNombre("DDR4 16 GB 3600 MHZ");
        r4.setDescripcion("Memoria ram de 16GB y 3600 MHz de velocidad");
        r4.setMarca("GSKILL Trident-Z NEO");
        r4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/RAM%2FG.Skill-Trident-Z-Neo-1.jpg?alt=media&token=0bce7c75-be46-48f0-bd16-16108391f2e8");
        r4.setPrecio(495000);
        r4.setTipoProducto(TipoProducto.ram.getTipo());
        prodServ.save(r4);

        Producto r5 = new Producto();
        r5.setNombre("DDR4 32 GB 3200 MHZ");
        r5.setDescripcion("Memoria ram de 8GB y 2600 MHz de velocidad");
        r5.setMarca("Kingston Hyper-X FURY");
        r5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/RAM%2F53.jpg?alt=media&token=d90bc13e-94f4-4531-b52f-90c0971850d9");
        r5.setPrecio(930000);
        r5.setTipoProducto(TipoProducto.ram.getTipo());
        prodServ.save(r5);
    }

    public void iniGpu(ProductoService prodServ){
        Producto g1 = new Producto();
        g1.setNombre("NVIDIA GT 710");
        g1.setDescripcion("Tarjeta gráfica con 2GB de almacenamiento, ram de tipo L2 chache");
        g1.setMarca("MSI");
        g1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/GPU%2F71.jpg?alt=media&token=eea3b9b7-a072-4056-9ec3-6f272c4da603");
        g1.setPrecio(240000);
        g1.setTipoProducto(TipoProducto.gpu.getTipo());
        prodServ.save(g1);

        Producto g2 = new Producto();
        g2.setNombre("NVIDIA 730");
        g2.setDescripcion("Tarjeta gráfica con 2GB de almacenamiento, ram de tipo DDR5");
        g2.setMarca("GIGABYTE");
        g2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/GPU%2F72.jpg?alt=media&token=f2fd4109-d333-4529-bad1-41b9709f289f");
        g2.setPrecio(345000);
        g2.setTipoProducto(TipoProducto.gpu.getTipo());
        prodServ.save(g2);

        Producto g3 = new Producto();
        g3.setNombre("NVIDIA GT 1030");
        g3.setDescripcion("Tarjeta gráfica con 2GB de almacenamiento, ram de tipo DDR5");
        g3.setMarca("MSI");
        g3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/GPU%2F73.jpg?alt=media&token=1bb63de4-4c9e-4c64-be0a-b84cd21ca435");
        g3.setPrecio(440000);
        g3.setTipoProducto(TipoProducto.gpu.getTipo());
        prodServ.save(g3);

        Producto g4 = new Producto();
        g4.setNombre("NVIDIA GTX 1050 TI");
        g4.setDescripcion("Tarjeta gráfica con 4GB de almacenamiento, ram de tipo DDR5");
        g4.setMarca("MSI");
        g4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/GPU%2F74.jpg?alt=media&token=383b8148-5215-444d-bfb8-7c742cca9835");
        g4.setPrecio(790000);
        g4.setTipoProducto(TipoProducto.gpu.getTipo());
        prodServ.save(g4);

        Producto g5 = new Producto();
        g5.setNombre("NVIDIA 1650");
        g5.setDescripcion("Tarjeta gráfica con 4GB de almacenamiento, ram de tipo DDR5");
        g5.setMarca("GIGABYTE");
        g5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/GPU%2F75.jpg?alt=media&token=fb525ea8-8e9c-443f-8add-5011612bdeab");
        g5.setPrecio(1050000);
        g5.setTipoProducto(TipoProducto.gpu.getTipo());
        prodServ.save(g5);
    }

    public void iniMotherBoards(ProductoService prodServ){
        Producto mother1 = new Producto();
        mother1.setNombre("H310M");
        mother1.setDescripcion("Modelo ATX con 2 ranuras para ram de tipo DDR4 y un socket tipo LGA1151");
        mother1.setMarca("ASUS");
        mother1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/MOTHERBOARD%2F27.jpg?alt=media&token=f37a63d4-2ba7-4d08-b7a3-7837104274d6");
        mother1.setPrecio(265000);
        mother1.setTipoProducto(TipoProducto.tarMadre.getTipo());
        prodServ.save(mother1);

        Producto mother2 = new Producto();
        mother2.setNombre("B550");
        mother2.setDescripcion("Modelo ATX con 4 ranuras para ram de tipo DDR4 y un socket tipo AM4");
        mother2.setMarca("GIGABYTE");
        mother2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/MOTHERBOARD%2Fb550.jpg?alt=media&token=6f9b5038-bb1a-4905-bb94-ed6fe54fd9f3");
        mother2.setPrecio(970000);
        mother2.setTipoProducto(TipoProducto.tarMadre.getTipo());
        prodServ.save(mother2);
        
        Producto mother3 = new Producto();
        mother3.setNombre("PRIME TRX40");
        mother3.setDescripcion("Modelo ATX con 8 ranuras para ram de tipo DDR4 y un socket tipo TRX4");
        mother3.setMarca("ASUS");
        mother3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/MOTHERBOARD%2Fasus-prime-trx40-pro-s.jpg?alt=media&token=b3a45453-5b06-4fd9-84c2-ec77e98b0540");
        mother3.setPrecio(1895000);
        mother3.setTipoProducto(TipoProducto.tarMadre.getTipo());
        prodServ.save(mother3);

        Producto mother4 = new Producto();
        mother4.setNombre("ROG STRIX TRX40");
        mother4.setDescripcion("Modelo ATX con 8 ranuras para ram de tipo DDR4 y un socket tipo TRX4");
        mother4.setMarca("ASUS");
        mother4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/MOTHERBOARD%2F52.jpg?alt=media&token=f5bec841-5ae8-40d0-bbfc-9419d2114612");
        mother4.setPrecio(2180000);
        mother4.setTipoProducto(TipoProducto.tarMadre.getTipo());
        prodServ.save(mother4);
  
        Producto mother5 = new Producto();
        mother5.setNombre("B365M");
        mother5.setDescripcion("Modelo ATX con 4 ranuras para ram de tipo DDR4 y un socket tipo LGA1151");
        mother5.setMarca("ASUS");
        mother5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/MOTHERBOARD%2F27.jpg?alt=media&token=f37a63d4-2ba7-4d08-b7a3-7837104274d6");
        mother5.setPrecio(315000);
        mother5.setTipoProducto(TipoProducto.tarMadre.getTipo());
        prodServ.save(mother5);
    }

    public void iniDiscos(ProductoService prodServ){

        Producto dduro1 = new Producto();
        dduro1.setNombre("KINGSTON A400");
        dduro1.setDescripcion("disco duro tipo M2 con capacidad de 120GB");
        dduro1.setMarca("KINGSTON");
        dduro1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISCODURO%2Fdisco-solido-m2-120gb-kingston-a400-sa400m8120g.jpg?alt=media&token=b872f289-48d7-4b36-bf2e-37146d8c4708");
        dduro1.setPrecio(115000);
        dduro1.setTipoProducto(TipoProducto.discoDuro.getTipo());
        prodServ.save(dduro1);

        Producto dduro2 = new Producto();
        dduro2.setNombre("CRUCIAL BX500");
        dduro2.setDescripcion("disco duro tipo SSD con capacidad de 480GB");
        dduro2.setMarca("CRUCIAL");
        dduro2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISCODURO%2FUnidad-Solida-Crucial-BX500-SSD-120GB-5.jpg?alt=media&token=60a2f542-7c30-4c58-90aa-807a43553e6a");
        dduro2.setPrecio(228000);
        dduro2.setTipoProducto(TipoProducto.discoDuro.getTipo());
        prodServ.save(dduro2);
  
        Producto dduro3 = new Producto();
        dduro3.setNombre("LEXAR NS100");
        dduro3.setDescripcion("disco duro tipo SSD con capacidad de 512GB");
        dduro3.setMarca("LEXAR");
        dduro3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISCODURO%2FlexarNS100.jpg?alt=media&token=ced648ca-fc7d-49f9-94e1-27db7c551da8");
        dduro3.setPrecio(260000);
        dduro3.setTipoProducto(TipoProducto.discoDuro.getTipo());
        prodServ.save(dduro3);

        Producto dduro4 = new Producto();
        dduro4.setNombre("SANDISK PLUS");
        dduro4.setDescripcion("disco duro tipo SSD con capacidad de 1TB");
        dduro4.setMarca("SANDISK");
        dduro4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISCODURO%2FSanDisk-SSD-Plus-1TB-SATA-III.jpg?alt=media&token=ee9abf4d-c390-42b9-af31-820b81f6fe0a");
        dduro4.setPrecio(395000);
        dduro4.setTipoProducto(TipoProducto.discoDuro.getTipo());
        prodServ.save(dduro4);

        Producto dduro5 = new Producto();
        dduro5.setNombre("WESTERN DIGITAL GREEN");
        dduro5.setDescripcion("disco duro tipo SSD con capacidad de 1TB");
        dduro5.setMarca("WESTERN DIGITAL");
        dduro5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISCODURO%2Fdisco-duro-ssd-western-digital-240gb-caviar-green-7mm-g2-5.jpg?alt=media&token=e4b7f9ec-2040-4aac-b1fa-208e06996947");
        dduro5.setPrecio(845000);
        dduro5.setTipoProducto(TipoProducto.discoDuro.getTipo());
        prodServ.save(dduro5);
    }

    public void iniFuentes(ProductoService prodServ){

        Producto fpoder1 = new Producto();
        fpoder1.setNombre("XPG 450 W");
        fpoder1.setDescripcion("fuente de tipo normal con potencia de 450W. Certificado 80 PLUS BRONZE");
        fpoder1.setMarca("XPG");
        fpoder1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/FUENTEPODER%2F121.jpg?alt=media&token=672e6b77-f2ce-49e8-b097-90692f6adc0f");
        fpoder1.setPrecio(169000);
        fpoder1.setTipoProducto(TipoProducto.fPoder.getTipo());
        prodServ.save(fpoder1);

        Producto fpoder2 = new Producto();
        fpoder2.setNombre("COUGAR 750 W");
        fpoder2.setDescripcion("fuente de tipo modular con potencia de 750W. Certificado 80 PLUS GOLD");
        fpoder2.setMarca("COUGAR");
        fpoder2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/FUENTEPODER%2F133.jpg?alt=media&token=e856e1a1-3301-4482-98b4-6776dc977df6");
        fpoder2.setPrecio(374000);
        fpoder2.setTipoProducto(TipoProducto.fPoder.getTipo());
        prodServ.save(fpoder2);

        Producto fpoder3 = new Producto();
        fpoder3.setNombre("XPG 850 W");
        fpoder3.setDescripcion("fuente de tipo modular con potencia de 850W. Certificado 80 PLUS GOLD");
        fpoder3.setMarca("XPG");
        fpoder3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/FUENTEPODER%2F121.jpg?alt=media&token=672e6b77-f2ce-49e8-b097-90692f6adc0f");
        fpoder3.setPrecio(459000);
        fpoder3.setTipoProducto(TipoProducto.fPoder.getTipo());
        prodServ.save(fpoder3);

        Producto fpoder4 = new Producto();
        fpoder4.setNombre("COUGAR 500 W");
        fpoder4.setDescripcion("fuente de tipo normal con potencia de 500W. Certificado 80 PLUS BRONZE");
        fpoder4.setMarca("COUGAR");
        fpoder4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/FUENTEPODER%2Ffuente-de-poder-cougar-vte500-500w-80p-bronze-cgr-bs-500.jpg?alt=media&token=45dc8e7f-a1ae-43e9-b320-545c4db36834");
        fpoder4.setPrecio(185000);
        fpoder4.setTipoProducto(TipoProducto.fPoder.getTipo());
        prodServ.save(fpoder4);

        Producto fpoder5 = new Producto();
        fpoder5.setNombre("CORSAIR 550 W");
        fpoder5.setDescripcion("fuente de tipo normal con potencia de 550W. Certificado 80 PLUS BRONZE");
        fpoder5.setMarca("CORSAIR");
        fpoder5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/FUENTEPODER%2Ffuente-de-alimentacion-modular-corsair-cx-series-cx550m-550w-atx-240v-80-plus-bronze-.jpg?alt=media&token=b0399fa4-0ad0-46e2-ba68-f64d8fdfc54f");
        fpoder5.setPrecio(239000);
        fpoder5.setTipoProducto(TipoProducto.fPoder.getTipo());
        prodServ.save(fpoder5);
    }

    public void iniDisipadores(ProductoService prodServ){

        Producto ds1 = new Producto();
        ds1.setNombre("AEROCOOL CORE PLUS");
        ds1.setDescripcion("Tipo de socket LGA1151. Refrigeración estandar");
        ds1.setMarca("AEROCOOL");
        ds1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISIPADOR%2F107.jpg?alt=media&token=877046aa-14f9-40c6-b0cf-04755f927900");
        ds1.setPrecio(99000);
        ds1.setTipoProducto(TipoProducto.disip.getTipo());
        prodServ.save(ds1);

        Producto ds2 = new Producto();
        ds2.setNombre("COOLER MASTER ML240P");
        ds2.setDescripcion("Tipo de socket AM4. Refrigeración liquida");
        ds2.setMarca("COOLER MASTER");
        ds2.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISIPADOR%2Fcoolermasterml240p.jpg?alt=media&token=27c4cbb9-fb33-479c-a52c-4b18913b4801");
        ds2.setPrecio(589000);
        ds2.setTipoProducto(TipoProducto.disip.getTipo());
        prodServ.save(ds2);

        Producto ds3 = new Producto();
        ds3.setNombre("AEROCOOL CYCLON 7F");
        ds3.setDescripcion("Tipo de socket AM4. Refrigeración estandar");
        ds3.setMarca("AEROCOOL");
        ds3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISIPADOR%2Faerocool-cylon-4-argb-disipador-cpu-001.jpg?alt=media&token=0f3b49da-ee17-40d2-bb9e-d41beec7f614");
        ds3.setPrecio(194000);
        ds3.setTipoProducto(TipoProducto.disip.getTipo());
        prodServ.save(ds3);

        Producto ds4 = new Producto();
        ds4.setNombre("COOLER MASTER I71c");
        ds4.setDescripcion("Tipo de socket AM4. Refrigeración estandar");
        ds4.setMarca("COOLER MASTER");
        ds4.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISIPADOR%2FCOOLER_MASTER_RR-I71C-20PC-R1_ICECAT_31201460.jpg?alt=media&token=8ae33969-1898-4a29-afc8-370362ae4b2b");
        ds4.setPrecio(75000);
        ds4.setTipoProducto(TipoProducto.disip.getTipo());
        prodServ.save(ds4);

        Producto ds5 = new Producto();
        ds5.setNombre("COOLER MASTER HYPER T20");
        ds5.setDescripcion("Tipo de socket AM4. Refrigeración estandar");
        ds5.setMarca("COOLER MASTER");
        ds5.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/DISIPADOR%2Fcooler-de-procesador-cooler-master-hyper-t20-rr-t20-20fk-r1.jpg?alt=media&token=70453be7-7385-4478-85b3-3bc172e93723");
        ds5.setPrecio(65000);
        ds5.setTipoProducto(TipoProducto.disip.getTipo());
        prodServ.save(ds5);
        
    }

    public void iniVentas(VentaService ventaserv,VdetalleService vdetalleService,BCryptPasswordEncoder bCryptPasswordEncoder){ 
     
        Usuario u2 = new Usuario();
        u2.setId(1001L);
        u2.setNombre("prueba");
        u2.setCorreo("prueba@hotmail.com");
        u2.setContrasena(bCryptPasswordEncoder.encode("123456"));
        u2.setTelefono(12345678);
        Rol nRol = new Rol();
        nRol.setNombre("Cliente");
        u2.setRol(nRol);

        Producto ch1 = new Producto();
        ch1.setId(6L);
        ch1.setNombre("AEROCOOL STREAK");
        ch1.setDescripcion("Chasis tipo Mid tower, compatible con tarjetas madre ATX");
        ch1.setMarca("AEROCOOL");
        ch1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/CHASIS%2F100.jpg?alt=media&token=8135e042-1c1d-45b2-8ba1-f58785d40016");
        ch1.setPrecio(229000);
        ch1.setTipoProducto(TipoProducto.chasis.getTipo());

        VentaDetalle vd1 = new VentaDetalle();
        vd1.setProducto(ch1);
        vd1.setCantidad(4);
        List<VentaDetalle> vdlista = new ArrayList<VentaDetalle>();
        
        Venta v1 = new Venta();
        v1.setFecha(new Date());
        v1.setPrecioTotal(ch1.getPrecio() * vd1.getCantidad());
        v1.setUsuario(u2);
        ventaserv.save(v1);
        vdetalleService.save(vd1);
        vdlista.add(vd1);
        v1.setDetallesVenta(vdlista);
        ventaserv.save(v1);

        Producto g3 = new Producto();
        g3.setId(18L);
        g3.setNombre("NVIDIA GT 1030");
        g3.setDescripcion("Tarjeta gráfica con 2GB de almacenamiento, ram de tipo DDR5");
        g3.setMarca("MSI");
        g3.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/GPU%2F73.jpg?alt=media&token=1bb63de4-4c9e-4c64-be0a-b84cd21ca435");
        g3.setPrecio(440000);
        g3.setTipoProducto(TipoProducto.gpu.getTipo());

        VentaDetalle vd2 = new VentaDetalle();
        vd2.setProducto(g3);
        vd2.setCantidad(2);
        List<VentaDetalle> vdlista2 = new ArrayList<VentaDetalle>();

        Venta v3 = new Venta();
        v3.setFecha(new Date());
        v3.setPrecioTotal(g3.getPrecio() * vd2.getCantidad());
        v3.setUsuario(u2);
        ventaserv.save(v3);
        vdetalleService.save(vd2);
        vdlista2.add(vd2);
        v3.setDetallesVenta(vdlista2);
        ventaserv.save(v3);

        Producto mother1 = new Producto();
        mother1.setId(21L);
        mother1.setNombre("H310M");
        mother1.setDescripcion("Modelo ATX con 2 ranuras para ram de tipo DDR4 y un socket tipo LGA1151");
        mother1.setMarca("ASUS");
        mother1.setFoto("https://firebasestorage.googleapis.com/v0/b/pcdrome-puj.appspot.com/o/MOTHERBOARD%2F27.jpg?alt=media&token=f37a63d4-2ba7-4d08-b7a3-7837104274d6");
        mother1.setPrecio(265000);
        mother1.setTipoProducto(TipoProducto.tarMadre.getTipo());

        VentaDetalle vd3 = new VentaDetalle();
        vd3.setProducto(mother1);
        vd3.setCantidad(10);
        List<VentaDetalle> vdlista3 = new ArrayList<VentaDetalle>();

        Venta v4 = new Venta();
        v4.setFecha(new Date());
        v4.setPrecioTotal(mother1.getPrecio() * vd3.getCantidad()); 
        v4.setUsuario(u2);
        ventaserv.save(v4);
        vdetalleService.save(vd3);
        vdlista3.add(vd3);
        v4.setDetallesVenta(vdlista3);
        ventaserv.save(v4);
    }

}

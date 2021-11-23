import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Componentes/header/header.component';
import { CpuComponent } from './Componentes/cpu/cpu.component';
import { RamComponent } from './Componentes/ram/ram.component';
import { HomeComponent } from './Componentes/home/home.component';
import { P404Component } from './Componentes/p404/p404.component';
import { GpuComponent } from './Componentes/gpu/gpu.component';
import { TarmadreComponent } from './Componentes/tarmadre/tarmadre.component';
import { DduroComponent } from './Componentes/dduro/dduro.component';
import { RefrigeracionComponent } from './Componentes/refrigeracion/refrigeracion.component';
import { FpoderComponent } from './Componentes/fpoder/fpoder.component';
import { ChasisComponent } from './Componentes/chasis/chasis.component';
import { LoginComponent } from './Componentes/login/login.component';
import { RegistrarComponent } from './Componentes/registrar/registrar.component';
import { HistorialComprasComponent } from './Componentes/historial-compras/historial-compras.component';
import { DetallesCompraComponent } from './Componentes/detalles-compra/detalles-compra.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InfoPagoComponent } from './Componentes/info-pago/info-pago.component';
import { ReporteVentasComponent } from './Componentes/reporte-ventas/reporte-ventas.component';
import { GestionProductosComponent } from './Componentes/gestion-productos/gestion-productos.component';
import { EditarProductoComponent } from './Componentes/editar-producto/editar-producto.component';
import { AgregarProductoComponent } from './Componentes/agregar-producto/agregar-producto.component';
import { ListaProductosComponent } from './Componentes/lista-productos/lista-productos.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { authInterceptor } from './Servicios/authInterceptor';
import { CarritoComponent } from './Componentes/carrito/carrito.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CpuComponent,
    RamComponent,
    HomeComponent,
    P404Component,
    GpuComponent,
    TarmadreComponent,
    DduroComponent,
    RefrigeracionComponent,
    FpoderComponent,
    ChasisComponent,
    LoginComponent,
    RegistrarComponent,
    HistorialComprasComponent,
    DetallesCompraComponent,
    InfoPagoComponent,
    ReporteVentasComponent,
    GestionProductosComponent,
    EditarProductoComponent,
    AgregarProductoComponent,
    ListaProductosComponent,
    CarritoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [/*{
    provide: HTTP_INTERCEPTORS,
    useClass: authInterceptor,
    multi: true
  }*/ 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

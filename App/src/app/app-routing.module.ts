import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgregarProductoComponent } from './Componentes/agregar-producto/agregar-producto.component';
import { CarritoComponent } from './Componentes/carrito/carrito.component';
import { ChasisComponent } from './Componentes/chasis/chasis.component';
import { CpuComponent } from './Componentes/cpu/cpu.component';
import { DduroComponent } from './Componentes/dduro/dduro.component';
import { DetallesCompraComponent } from './Componentes/detalles-compra/detalles-compra.component';
import { EditarProductoComponent } from './Componentes/editar-producto/editar-producto.component';
import { FpoderComponent } from './Componentes/fpoder/fpoder.component';
import { GestionProductosComponent } from './Componentes/gestion-productos/gestion-productos.component';
import { GpuComponent } from './Componentes/gpu/gpu.component';
import { HistorialComprasComponent } from './Componentes/historial-compras/historial-compras.component';
import { HomeComponent } from './Componentes/home/home.component';
import { InfoPagoComponent } from './Componentes/info-pago/info-pago.component';
import { ListaProductosComponent } from './Componentes/lista-productos/lista-productos.component';
import { LoginComponent } from './Componentes/login/login.component';
import { P404Component } from './Componentes/p404/p404.component';
import { RamComponent } from './Componentes/ram/ram.component';
import { RefrigeracionComponent } from './Componentes/refrigeracion/refrigeracion.component';
import { RegistrarComponent } from './Componentes/registrar/registrar.component';
import { ReporteVentasComponent } from './Componentes/reporte-ventas/reporte-ventas.component';
import { TarmadreComponent } from './Componentes/tarmadre/tarmadre.component';
import { AuthGuard } from './Guard/auth.guard';

const routes: Routes = [
  { path: 'Procesadores', component: CpuComponent },
  { path: 'RAM', component: RamComponent },
  { path: 'Graficas', component: GpuComponent },
  { path: 'Tarjetas_Madre', component: TarmadreComponent },
  { path: 'Discos_duros', component: DduroComponent },
  { path: 'Refrigeracion', component: RefrigeracionComponent },
  { path: 'Fuentes_Poder', component: FpoderComponent },
  { path: 'Chasises', component: ChasisComponent },
  { path: 'iniciar_sesion', component: LoginComponent },
  { path: 'registrarse', component: RegistrarComponent },
  { path: 'historial', component: HistorialComprasComponent },
  { path: 'detallesCompra/:nOrden', component: DetallesCompraComponent },
  { path: 'infoTarjeta', component: InfoPagoComponent },
  { path: 'reporte_ventas', component: ReporteVentasComponent, canActivate: [AuthGuard] },
  { path: 'gestionar_productos', component: GestionProductosComponent, canActivate: [AuthGuard],
    children: [
      { path: 'lista_productos', component: ListaProductosComponent, canActivate: [AuthGuard] },
      { path: 'crear_producto', component: AgregarProductoComponent, canActivate: [AuthGuard] },
      { path: 'editar_producto/:id', component: EditarProductoComponent, canActivate: [AuthGuard] }
    ]
  },
  { path: 'carrito',component: CarritoComponent},
  { path: '', component: HomeComponent },
  { path: '**', component: P404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

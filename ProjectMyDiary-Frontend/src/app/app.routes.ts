import { Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { RoleGuard } from './core/guards/role.guard';

export const routes: Routes = [

{
    path: '',
    canActivate: [AuthGuard],
    component: MainLayoutComponent,
    children:[
        {path:'dashboard', component: DashboardComponent},
        {path:'diary',loadChildren:() => import('./features/diary/diary-routing.module').then(m => m.DiaryRoutingModule)},
        {path:'weather',loadChildren:() => import('./features/weather/weather-routing.module').then(m => m.WeatherRoutingModule)},
        {path:'countries',loadChildren:() => import('./features/countries/countries-routing.module').then(m => m.CountriesRoutingModule)},
        {path:'currency',loadChildren:() => import('./features/currency/currency-routing.module').then(m => m.CurrencyRoutingModule)},
        {
            path: 'admin',
            canActivate: [RoleGuard],
            data:{ roles: ['ADMIN'] },
          //  loadChildren: () => import('./features/admin/admin-routing.module').then(m => m.AdminRoutingModule)
        }
    ]
 },


 {path:'login', loadChildren:() => import('./features/auth/auth-routing.module').then(m => m.AuthRoutingModule)},
 {path:'**', redirectTo:'dashboard'}
];

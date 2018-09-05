import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from '@angular/router/src/config';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { LoginUserComponent } from './components/login-user/login-user.component'

const authRoutes : Routes = [
    {
        path: '',
        children: [
            {
                path: '',
                redirectTo: '/login',
                pathMatch: 'full'
            },
            {
                path: 'register',
                component: RegisterUserComponent,
            },
            {
                path: 'login',
                component: LoginUserComponent,
            }
        ]
    }  
];
@NgModule({
    imports: [
        RouterModule.forChild(authRoutes)
    ],
    declarations: [ ],
    exports: [ RouterModule],
    providers: [],
  })
  export class AuthenticationRouterModule { }
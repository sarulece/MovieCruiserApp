import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule} from '@angular/common/http';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { LoginUserComponent } from './components/login-user/login-user.component';
import { MatInputModule } from '@angular/material/input';
import { FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material';
import { AuthenticationService } from './authentication.service'
import { AuthenticationRouterModule } from './authentication-router.module';
import { AlertComponent } from './components/alert/alert.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatButtonModule,
    FormsModule,
    MatInputModule,
    AuthenticationRouterModule
  ],
  declarations: [RegisterUserComponent
              , LoginUserComponent, AlertComponent],
  exports: [ RegisterUserComponent, LoginUserComponent, AuthenticationRouterModule, AlertComponent], 
  providers: [AuthenticationService ],
})
export class AuthenticationModule { }

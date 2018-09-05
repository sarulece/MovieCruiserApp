import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatToolbar } from '@angular/material';
import { MatButtonModule } from '@angular/material/button'
import { MovieModule } from './modules/movie/movie.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthguardService } from './authGuard.service'
import { AlertService } from './services/alert.service';

const appRoutes = [
  {
    path : '',
    redirectTo : '/login',
    pathMatch : 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    BrowserModule,
    MovieModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [AuthguardService, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }

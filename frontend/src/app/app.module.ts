import { AuthInterceptor } from './auth-interceptor';
import { ModalService } from './../services/modal.service';
import { VerifyComponent } from './verify/verify.component';
import { LoginService } from './../services/login.service';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login/login.component';

import { RegisterComponent } from './register/register.component';
import { RegisterService } from 'src/services/register.service';
import { RentacarComponent } from './rentacar/rentacar.component';
import { RentacarService } from 'src/services/rentacar.service';
import { RacprofileComponent } from './racprofile/racprofile.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';

import { RentacarPreviewComponent } from './rentacar-preview/rentacar-preview.component';
import { RacprofilePreviewComponent } from './racprofile-preview/racprofile-preview.component';
import { DatePipe } from '@angular/common';




const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verify/:id', component: VerifyComponent},
  {path: 'rentacar', component: RentacarComponent},
  {path: 'rentacar/:id', component: RacprofileComponent},
  {path: 'error45', component: ErrorComponent},
  {path: 'home', component: HomeComponent},
  {path: 'rentacarPreview', component: RentacarPreviewComponent},
  {path: 'rentacarPreview/:id', component: RacprofilePreviewComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    VerifyComponent,
    RentacarComponent,
    RacprofileComponent,
    ErrorComponent,
    HomeComponent,
    RentacarPreviewComponent,
    RacprofilePreviewComponent,
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,{
        enableTracing: true
      }
    )
  ],
  providers: [LoginService, RegisterService,RentacarService,ModalService,{ provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
    },DatePipe,],
  bootstrap: [AppComponent]
})
export class AppModule { }

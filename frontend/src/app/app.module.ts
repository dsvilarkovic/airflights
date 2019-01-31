import { AuthInterceptor } from './auth-interceptor';
import { ModalService } from './../services/modal.service';
import { VerifyComponent } from './verify/verify.component';
import { LoginService } from './../services/login.service';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { RegisterService } from 'src/services/register.service';
import { RentacarComponent } from './rentacar/rentacar.component';
import { RentacarService } from 'src/services/rentacar.service';
import { RacprofileComponent } from './racprofile/racprofile.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';

import { UserProfileComponent } from './user-profile/user-profile/user-profile.component';
import { HotelAddComponent } from './hotel/hotel-add/hotel-add.component';
import { HotelListComponent } from './hotel/hotel-list/hotel-list.component';
import { HotelEditComponent } from './hotel/hotel-edit/hotel-edit.component';
import { HotelService } from '../services/hotel.service';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomAddComponent } from './room/room-add/room-add.component';
import { ExtrasListComponent } from './hotel_extras/extras-list/extras-list.component';
import { RoomEditComponent } from './room/room-edit/room-edit.component';
import { ExtrasAddComponent } from './hotel_extras/extras-add/extras-add.component';
import { ExtrasEditComponent } from './hotel_extras/extras-edit/extras-edit.component';
import { HotelChartComponent } from './hotel/hotel-chart/hotel-chart.component';
import { HotelRevenuesComponent } from './hotel/hotel-revenues/hotel-revenues.component';
import { PromotionInitComponent } from './room/promotion-init/promotion-init.component'

import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { AdminFlightsComponent } from './admin/admin-flights/admin-flights.component';
import { AdminHotelsComponent } from './admin/admin-hotels/admin-hotels.component';
import { AdminRentACarComponent } from './admin/admin-rent-a-car/admin-rent-a-car.component';
import { AdminBonusesComponent } from './admin/admin-bonuses/admin-bonuses.component';
import { AdminProfileComponent } from './admin/admin-profile/admin-profile.component';

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verify/:id', component: VerifyComponent},
  {path: 'rentacar', component: RentacarComponent},
  {path: 'rentacar/:id', component: RacprofileComponent},
  {path: 'error45', component: ErrorComponent},
  {path: 'home', component: HomeComponent},
  {path: 'hotel/list', component: HotelListComponent },
  {path: 'hotel/add', component: HotelAddComponent },
  {path: 'hotel/edit/:id', component: HotelEditComponent },
  {path: 'hotel/edit/:id/rooms', component: RoomListComponent },
  {path: 'hotel/edit/:id/addRoom', component: RoomAddComponent },
  {path: 'hotel/edit/:idh/rooms/:id/edit', component: RoomEditComponent },
  {path: 'hotel/edit/:id/extras', component: ExtrasListComponent },
  {path: 'hotel/edit/:id/addExtra', component: ExtrasAddComponent },
  {path: 'hotel/edit/:idh/extras/:id/edit', component: ExtrasEditComponent },
  {path: 'hotel/edit/:id/charts', component: HotelChartComponent },
  {path: 'hotel/edit/:id/revenues', component: HotelRevenuesComponent },
  {path: 'hotel/edit/:idh/:id/promo', component: PromotionInitComponent },
  {path: 'admin/profile/:id', component: AdminProfileComponent},
  {path: 'admin/flights', component: AdminFlightsComponent},
  {path: 'admin/hotels', component: AdminHotelsComponent},
  {path: 'admin/rac', component: AdminRentACarComponent},
  {path: 'admin/bonus', component: AdminBonusesComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    VerifyComponent,
    RentacarComponent,
    RacprofileComponent,
    ErrorComponent,
    HotelListComponent,
    HotelEditComponent,
    HotelAddComponent,
    UserProfileComponent,
    HotelAddComponent,
    HotelListComponent,
    HotelEditComponent,
    RoomListComponent,
    RoomAddComponent,
    ExtrasListComponent,
    RoomEditComponent,
    ExtrasAddComponent,
    ExtrasEditComponent,
    HotelChartComponent,
    HotelRevenuesComponent,
    PromotionInitComponent,
    AdminFlightsComponent,
    AdminHotelsComponent,
    AdminRentACarComponent,
    AdminBonusesComponent,
    AdminProfileComponent,
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    NgMultiSelectDropDownModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,{
        enableTracing: true
      }
    )
  ],
  providers: [LoginService, RegisterService,RentacarService, HotelService, ModalService,{ provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
    },],
  bootstrap: [AppComponent]
})
export class AppModule { }

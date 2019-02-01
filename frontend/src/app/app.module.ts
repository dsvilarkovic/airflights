import { AuthInterceptor } from './auth-interceptor';
import { ModalService } from './../services/modal.service';
import { VerifyComponent } from './verify/verify.component';
import { LoginService } from './../services/login.service';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HttpModule } from '@angular/http';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


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

import { UserProfileComponent} from './user-profile/user-profile.component';

import { MessageComponent } from './message/message.component';
import { RentacarPreviewComponent } from './rentacar-preview/rentacar-preview.component';
import { RacprofilePreviewComponent } from './racprofile-preview/racprofile-preview.component';
import { DatePipe } from '@angular/common';
import { AirplaneTableComponent } from './airplane-table/airplane-table.component';
import { ConfigSeatsComponent } from './config-seats/config-seats.component';
import { AdminsComponent } from './admin/admins/admins.component';
import { RoomService } from 'src/services/room.service';
import { AdminsService } from 'src/services/admins.service';
import { AdminAddComponent } from './admin/admin-add/admin-add.component';
import { AdminRentACarAddComponent } from './admin/admin-rent-a-car-add/admin-rent-a-car-add.component';
import { AdminAirlineAddComponent } from './admin/admin-airline-add/admin-airline-add.component';
import { AddPlaneComponent } from './add-plane/add-plane.component';

import { AdminHotelProfileComponent } from './admin/admin-hotel-profile/admin-hotel-profile.component';
import { HotelRoomsComponent } from './hotel/hotel-rooms/hotel-rooms.component';

import { RentACarDiscountComponent } from './rent-acar-discount/rent-acar-discount.component';
import { AuthhomepageComponent } from './authhomepage/authhomepage.component';
import { ListAirflightsComponent } from './list-airflights/list-airflights.component';
import { ListHotelsComponent } from './list-hotels/list-hotels.component';
import { ListRentacarsComponent } from './list-rentacars/list-rentacars.component';
import { ListFriendsComponent } from './list-friends/list-friends.component';



const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verify/:id', component: VerifyComponent},
  {path: 'rentacar', component: RentacarComponent},
  {path: 'rentacar/:id', component: RacprofileComponent},
  {path: 'error45', component: ErrorComponent},
  {path: 'home', component: HomeComponent},

  {path: 'hotel/list', component: HotelListComponent },
  {path: 'admin/hotel/add', component: HotelAddComponent },
  {path: 'admin/hotel/profile', component: AdminHotelProfileComponent},
  {path: 'admin/rac/add', component: AdminRentACarAddComponent },
  {path: 'admin/airline/add', component: AdminAirlineAddComponent },
  {path: 'hotel/edit/:id', component: HotelEditComponent },
  {path: 'hotel/edit/rooms/list', component: RoomListComponent },
  {path: 'hotel/edit', component: HotelEditComponent },
  {path: 'hotel/edit/:id/addRoom', component: RoomAddComponent },
  {path: 'hotel/edit/:idh/rooms/:id/edit', component: RoomEditComponent },
  {path: 'hotel/edit/extras/list', component: ExtrasListComponent },
  {path: 'hotel/edit/:id/addExtra', component: ExtrasAddComponent },
  {path: 'hotel/edit/:idh/extras/:id/edit', component: ExtrasEditComponent },
  {path: 'hotel/charts', component: HotelChartComponent },
  {path: 'hotel/revenues', component: HotelRevenuesComponent },
  {path: 'hotel/edit/:idh/:id/promo', component: PromotionInitComponent },
  {path: 'admin/profile', component: AdminProfileComponent},
  {path: 'admin/flights', component: AdminFlightsComponent},
  {path: 'admin/hotels', component: AdminHotelsComponent},
  {path: 'admin/rac', component: AdminRentACarComponent},
  {path: 'admin/admins', component: AdminsComponent},
  {path: 'admin/bonus', component: AdminBonusesComponent},
  {path: 'admin/:id/:type/newAdmin', component: AdminAddComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'userProfile', component: UserProfileComponent},
  {path: 'rentacarPreview', component: RentacarPreviewComponent},
  {path: 'rentacarPreview/:id', component: RacprofilePreviewComponent},
  {path: 'seatConfig', component : ConfigSeatsComponent},


  {path: 'rooms/list', component : HotelRoomsComponent},
  {path: ':id/rooms/list', component : HotelRoomsComponent},

  {path: 'rentacarDiscount/:id/:date1/:date2', component: RentACarDiscountComponent},
  {path: 'authHomePage/:id',component: AuthhomepageComponent},
  {path: 'listAirflights', component: ListAirflightsComponent},
  {path: 'listHotels', component: ListHotelsComponent},
  {path: 'listRentacars', component: ListRentacarsComponent},
  {path: 'listFriends/:id', component: ListFriendsComponent}

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
    AdminsComponent,
    HomeComponent,
    UserProfileComponent,
    MessageComponent,
    RentacarPreviewComponent,
    RacprofilePreviewComponent,
    AirplaneTableComponent,
    ConfigSeatsComponent,
    AdminAddComponent,
    AdminRentACarAddComponent,
    AdminAirlineAddComponent,
    AddPlaneComponent,
    AdminHotelProfileComponent,
    HotelRoomsComponent,
    RentACarDiscountComponent,
    AuthhomepageComponent,
    ListAirflightsComponent,
    ListHotelsComponent,
    ListRentacarsComponent,
    ListFriendsComponent,
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,{
        enableTracing: true
      }
    ),
  ],
  providers: [LoginService, RegisterService,RentacarService, RoomService, AdminsService, HotelService, ModalService,{ provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
    },DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

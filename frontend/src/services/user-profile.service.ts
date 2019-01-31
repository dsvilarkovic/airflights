import { Injectable } from '@angular/core';
import { UserToUpdate } from "../app/user-profile/user-to-update";
import { Observable, of} from 'rxjs';
import { MessageService} from "../services/message.service";
import {HttpClient, HttpHeaders} from '@angular/common/http'


@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  
  url  : string = "//localhost:8080/api/userProfile";
  user : UserToUpdate = new UserToUpdate();
  constructor(private messageService : MessageService, private httpClient : HttpClient) { 
  }

  private log(message: string){
    this.messageService.set('UserProfile service: ${message}');
  }

  getLoggedUser(): Observable<UserToUpdate>{
    //return of(this.user);

    //this.httpClient.get<User>(this.url + "/get")
                      //.subscribe(loggedUser => this.user = loggedUser);
    //this.messageService.set('Logged as ' + this.user.firstName + " " + this.user.lastName);
    //return of(this.user);
    return this.httpClient.get<UserToUpdate>(this.url + "/get");
  }

  updateUser(user: UserToUpdate): void{

    if(user == null || user == undefined){
      this.messageService.set('There is no logged in user!', "warning");
      return;
    }

    var resultCode;
    var resultBody;
    this.httpClient.put<UserToUpdate>(this.url + "/update", user, {observe: 'response'})
            .subscribe(
              response => {
                resultCode = response.status;
                resultBody = response.body;
                this.messageService.set('Personal data has been updated!', "success");
              },
              error => {
                resultCode = error.status;
                resultBody = error.error;
                this.messageService.set(resultBody.text, "danger");
              });
  }
}

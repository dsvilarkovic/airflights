import { Component, OnInit } from '@angular/core';
import { UserProfileService } from '../../services/user-profile.service';
import { UserToUpdate } from '../user-profile/user-to-update';
import { MessageService } from 'src/services/message.service';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  loggedUser: UserToUpdate;
  confirmPassword: string;

  constructor(private userProfileService: UserProfileService, private messageService: MessageService) {

   }

  ngOnInit() {
    this.getLoggedUser();
  }

  getLoggedUser(): void{
    //this.loggedUser = this.userProfileService.getLoggedUser();
    this.userProfileService.getLoggedUser().subscribe(loggedUser => this.loggedUser = loggedUser);
  }
  
  updateUser(): void{
    //proveri da li su oba null, onda ne treba praviti problem
    if(Boolean(this.confirmPassword) == false && Boolean(this.loggedUser.newPassword) == false){
      this.userProfileService.updateUser(this.loggedUser);
      return;
    }
    //proveri drugo da li je su neki null/undefined ili prazni
    else if(Boolean(this.confirmPassword) == false || Boolean(this.loggedUser.newPassword) == false){
      this.messageService.set("Wrong password confirmation, please try again", "danger");
    }
    //proveri da li su isti
    if(this.confirmPassword.trim() === this.loggedUser.newPassword.trim())
        this.userProfileService.updateUser(this.loggedUser);
    else
        this.messageService.set("Wrong password confirmation, please try again", "danger");

      
  }

}

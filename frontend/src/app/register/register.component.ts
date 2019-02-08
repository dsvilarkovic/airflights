import { SignUpInfo } from './../forms/registerForm';
import { TokenStorageService } from './../../services/auth/token-storage.service';
import { AuthService } from './../../services/auth/auth.service';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/services/register.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';



export class RegistrationValidator {
    static validate(registrationFormGroup: FormGroup) {
      let password = registrationFormGroup.controls.password.value;
      let confirmPassword = registrationFormGroup.controls.confirmPassword.value;

      if (confirmPassword.length <= 0) {
        return null;
      }

      if(confirmPassword !== password) {
        return {
          doesMatchPassword: true
        };
      }

      return null;
    }
}




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  registrationFormGroup : FormGroup;
  passwordFormGroup: FormGroup;
  temp:User = new User();
  signupInfo : SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private registerService: RegisterService, private router : Router,
                      private formBuilder: FormBuilder,private authService: AuthService, private tokenStorage: TokenStorageService) {
                        this.passwordFormGroup = this.formBuilder.group({
                          password: ['', Validators.required],
                          confirmPassword: ['',Validators.required]
                        }, {
                         validator: RegistrationValidator.validate.bind(this)
                        });

                        this.registrationFormGroup = this.formBuilder.group({
                          email: ['', Validators.required],
                          passwordFormGroup: this.passwordFormGroup
                        });

                       }


  ngOnInit() {
  }

  home() {
    this.router.navigate(['/home']);
  }
  register() {
      this.signupInfo = new SignUpInfo(
          this.user.firstName,
          this.user.email,
          this.user.password,
          this.user.lastName,
          this.user.address,
          this.user.phoneNumber,
      )

     

      this.authService.signUp(this.signupInfo).subscribe(data=> {
        this.temp = data;
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.registerService.sendMail(this.temp.id).subscribe(data=> {
          localStorage["sent"] = true;
          this.router.navigate(['/']);
        }, error => {
          console.log(error);
        })
      })


     /* this.registerService.register(this.user).subscribe(data => {
        this.user = data;      
        alert("Uspesno registrovanje! Vasu registraciju morate da potvrdite na vasem e-mailu!");
        this.router.navigate(["/login/"]);
        this.registerService.sendMail(this.user.id).subscribe(data => {
          alert("DATA?");
        })
      })*/
  }


}

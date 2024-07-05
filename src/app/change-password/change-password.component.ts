import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LoginDetails } from '../login-details';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {

  ChangePassword :FormGroup| any
  constructor(private Route:Router,private authService:AuthService, private loginDetails:LoginDetails,private notify:NotificationService,private fb: FormBuilder){}
  ngOnInit(): void {
    this.ChangePassword = this.fb.group({
      oldPassword : [''],
      newPassword :[''],
      confirmPassword :['']
    },{ validator: this.passwordMatchValidator })
  }
  CancelButton(){
    this.Route.navigateByUrl('/homePage')
  }
  passwordMatchValidator(formGroup: FormGroup): { [key: string]: boolean } | null {
    const password = formGroup.get('password');
    const confirmPassword = formGroup.get('confirmPassword');
    if (password && confirmPassword && password.value !== confirmPassword.value) {
      return { mismatch: true };
    }
    return null;
  }

  onSubmit(){
    const {oldPassword,newPassword,confirmPassword}=this.ChangePassword.value
            this.authService.UpdatePassword(this.loginDetails._emailID,newPassword).subscribe( res =>{
              console.log(res + 'this is change password ')
              this.notify.showSuccess('Password Updated Succesfully')
            })
  }
}
 
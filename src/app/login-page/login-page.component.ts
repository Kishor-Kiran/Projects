import { Component } from '@angular/core';
import { Route, Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from '../notification.service';
import { LoginDetails } from '../login-details';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  loginForm: FormGroup|any ;
  email!: string;
  password !: string 


  constructor(private fb: FormBuilder, private authService: AuthService,private route:Router,private notify:NotificationService,private UserData:LoginDetails) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).subscribe(
        response => {
          console.log('Backend response:', response.text);
          if (response.text === 'Login successful') { // Add null check here
            this.UserData._emailID=response.email
            this.UserData._employeeID=response.UserId
            this.UserData._fullname=response.username
            this.UserData._RegStatus=response.RegStatus
            this.UserData._mobile=response.Mobile
            this.UserData._roleid=response.roleid
            console.log(this.UserData._RegStatus+'This is status')
            sessionStorage.setItem ('UserId',this.UserData._employeeID)
            sessionStorage.setItem('username',this.UserData._fullname)
            sessionStorage.setItem('email',this.UserData._emailID)
            sessionStorage.setItem('RegStatus',this.UserData._RegStatus)
            sessionStorage.setItem('Mobile',this.UserData._mobile)
            sessionStorage.setItem('roleid',this.UserData._roleid)
            this.notify.showSuccess('HI '+response.username+' WELCOME TO FORUM IT SOLUTIONS' )
            this.route.navigateByUrl('/homePage');
          } 
        },
        error => {
          console.error('Login failed', error);
          if (error.error instanceof ErrorEvent) {
            alert('An error occurred:');
          }else if(error.error.text === 'Incorrect Email id Or Password'){
            this.notify.showError("Incorrect Email id Or Password")
          }else {
            console.error('Backend login failed');
            this.notify.showError(error.error.text + ' Please Register Your Email ID')  
          }
          
        }
      );
    }
  }
  // onSubmit(){
  //   this.authService.loginVerification(this.email,this.password).subscribe(res =>{
  //     res ==this.loginForm
  //     console.log(res + 'Response login')
  //   })
  // }

  
  
  
  

  newUserRegister(){
    this.route.navigateByUrl('/UserRegister')
  }


  userLogin(){
    console.log()
  }
}

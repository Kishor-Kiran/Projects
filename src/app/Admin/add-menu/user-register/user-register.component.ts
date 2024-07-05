import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AuthService } from '../../../auth.service';
import { LoginDetails } from '../../../login-details';
import { NotificationService } from '../../../notification.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit{

  RegisterForm: FormGroup| any ;

  constructor(private fb: FormBuilder,private authService: AuthService,private route:Router,private notify : NotificationService,private UserData :LoginDetails) { }

  ngOnInit(): void {
    this.RegisterForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email,]],
      // ,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$')
      mobileNumber :['', [Validators.required, Validators.maxLength(10), Validators.pattern('(0|91)?[6-9][0-9]{9}')]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
   
  }

  passwordMatchValidator(formGroup: FormGroup): { [key: string]: boolean } | null {
    const password = formGroup.get('password');
    const confirmPassword = formGroup.get('confirmPassword');
    if (password && confirmPassword && password.value !== confirmPassword.value) {
      return { mismatch: true };
    }
    return null;
  }

  onSubmit(): void {
    if (this.RegisterForm.valid) {
      const { name,email, mobileNumber,password } = this.RegisterForm.value;
      this.authService.Register(name, email, mobileNumber,password).subscribe(
        response => {
          if(response == 500){
            this.notify.showError('Email Id Or Mobile Number Is already Registered Please Login ')
        
          }else{
            this.RegisterForm.reset()
            // this.route.navigateByUrl('/LoginPage')
            this.notify.showSuccess('Register successfully')
          
        } },
        error => {
          console.error('Register failed', error);
          // Handle login error here
        }
      );
    }
  }

  onCancel(){
    sessionStorage.clear();
    this.route.navigateByUrl('/homePage')
  }
  
}



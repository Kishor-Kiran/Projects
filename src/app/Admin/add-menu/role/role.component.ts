import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { subscribeOn } from 'rxjs';
import { AuthService } from '../../../auth.service';
import { LoginDetails } from '../../../login-details';
import { NotificationService } from '../../../notification.service';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent {

roles :any='---Select---'
name :any ='---Select---'
 RoleForm: FormGroup| any ;
 RoleDetails ={
  name :'',
  roles :'',
  userId :this.UserData._employeeID
 }
 Username ={
  name : '',
  userid : ''
 }
 constructor(private fb: FormBuilder,
  private authService: AuthService,
  private route:Router,
  private notify : NotificationService,
  private UserData :LoginDetails) { }



  ngOnInit(): void {
    this.RoleForm = this.fb.group({
      name: [''],
      roles: [''],
      userId :['']
    });
   this.fetchRoles();
   this.fetchUser()
  }

  onSubmit() : void{
 if(this.RoleForm.valid){
      const{UserName,Roles}=this.RoleForm.valid ;
      this.RoleForm.userId=this.UserData._employeeID
      this.authService.roles(this.RoleDetails).subscribe(res =>{
            console.log('res roles '+this.RoleForm.userId)
            if(res ==200){
              this.RoleForm.reset();
              this.notify.showSuccess('Role Mapped succesfully to '+this.RoleDetails.name)
            }else if(res==500){
        this.notify.showError('Role is Already Mapped')
            }
      })
    }
 }

 onCancel(){
  sessionStorage.clear();
  this.route.navigateByUrl('/homePage')
 }

 fetchRoles(){
  this.authService.fetchRole().subscribe(res =>{
    this.roles = res
    console.log('Res of username :'+this.roles)
  })
 }

 fetchUser(){
  this.authService.fetchUserName().subscribe(res =>{
    this.name = res
    console.log('this is userName ' + this.name)
  })
 }

}

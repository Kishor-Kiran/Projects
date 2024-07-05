import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule,Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { UserRegisterComponent } from './Admin/add-menu/user-register/user-register.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { RoleComponent } from './Admin/add-menu/role/role.component';
import { RoleCreationComponent } from './Admin/add-menu/role-creation/role-creation.component';
import { DepartmentsComponent } from './Masters/departments/departments.component';
import { PatientRegisterComponent } from './Billing/PatientRegister/patient-register/patient-register.component';


const routes: Routes =[
 
{path :"LoginPage",component:LoginPageComponent},
{path :"UserRegister",component:UserRegisterComponent},
{path:"homePage",component:HomePageComponent},
{path:"ChangePassword",component:ChangePasswordComponent},
{path:"",component:HomePageComponent},
{path:"Role",component:RoleComponent},
{path:"Rolecreation",component:RoleCreationComponent},
{path:"Department",component:DepartmentsComponent},
{path : "Register",component:PatientRegisterComponent}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }

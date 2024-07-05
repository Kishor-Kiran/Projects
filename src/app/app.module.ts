import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { AppRoutingModule } from './app-routing.module';
import { DatePipe } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { UserRegisterComponent } from './Admin/add-menu/user-register/user-register.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ToastrModule } from 'ngx-toastr';
import { LoginDetails } from './login-details';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Required for animations
import { BackButtonDisableModule } from 'angular-disable-browser-back-button';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { RoleComponent } from './Admin/add-menu/role/role.component';
import { SidenavbarComponent } from './sidenavbar/sidenavbar.component';
import { RoleCreationComponent } from './Admin/add-menu/role-creation/role-creation.component';
import { DepartmentsComponent } from './Masters/departments/departments.component';
import { PatientRegisterComponent } from './Billing/PatientRegister/patient-register/patient-register.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HeaderComponent,
    UserRegisterComponent,
    HomePageComponent,
    ChangePasswordComponent,
    RoleComponent,
    SidenavbarComponent,
    RoleCreationComponent,
    DepartmentsComponent,
    PatientRegisterComponent
    
    
  ],
  imports: [
    BrowserModule,
    FormsModule ,
    HttpClientModule,
    RouterModule,
    FormsModule,ReactiveFormsModule, AppRoutingModule ,
    BrowserAnimationsModule,
   ToastrModule.forRoot({
    maxOpened: 1,
    autoDismiss: true,
    preventDuplicates: true,
    timeOut: 3000,
    positionClass: 'toast-top-right'
  }),
  BackButtonDisableModule.forRoot(
    {
      preserveScroll:true
    }
  ),

  ],
  exports :[],
  providers: [LoginDetails],
  bootstrap: [AppComponent]
})
export class AppModule {

 }

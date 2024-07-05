import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-patient-register',
  templateUrl: './patient-register.component.html',
  styleUrls: ['./patient-register.component.css']
})
export class PatientRegisterComponent {

  patientRegister :FormGroup |any

  RegisterPatient ={
    patientName : '' ,
    drName : '' ,
    dOB : '' ,
    gender : '' ,
    Age: '' ,
    adharNumber: '' ,
    mobileNumber: '' ,
    ciTy: '' ,
    staTe: '' ,
    address: '' ,
    stReet : '' ,
    Area : '' ,
    Pincode: '' 

  }

  constructor(private fb:FormBuilder,private Route:Router,private auth:AuthService){}

  ngOnInit(){
    this.patientRegister=this.fb.group({
      patientName :[''],
      drName :[''],
      dOB :[''],
      gender :[''],
      Age:[''],
      adharNumber:[''],
      mobileNumber:[''],
      ciTy:[''],
      staTe:[''],
      address:[''],
      stReet :[''],
      Area :[''],
      Pincode:['']

    })
  }

  OnSubmit(){
    const{patientName,drName,dOB,gender,Age,adharNumber,mobileNumber,ciTy,staTe,address,stReet,Area,Pincode}=this.patientRegister.value
    this.auth.registration(this.RegisterPatient).subscribe(res=>{
      
    })
  }

  CancelButton(){
    this.Route. navigateByUrl("/Homepage")
  }
}

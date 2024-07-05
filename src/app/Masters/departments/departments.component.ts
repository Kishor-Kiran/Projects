import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent {
  departments :FormGroup  | any
  department :string="---Select---"
  designationName:string|any
  designationCode:string |any
  descipline :string |any

  constructor(private fb:FormBuilder,private auth:AuthService,private notify:NotificationService,private route:Router){}

  ngOnInit(){
this.departments=this.fb.group({
  designationName:[''],
  designationCode:[''],
  department :[''],
  descipline :['']
})
}

onSubmit(){
const{designationName,designationCode,department,descipline} =this.departments.value



if(this.designationName == null){
  this.notify.showError('Please Enter Designation Name')
}else if(this.designationCode==null){
  this.notify.showError('Please Enter Designation Code')
}else if(this.department=='---Select---'){
  this.notify.showError('Please Select Department')
}else if(this.descipline==null){
  this.notify.showError('Please Enter descipline')
}else{
  this.notify.showSuccess('Department Created Successfully')
  this.auth.department(designationName,designationCode,department,descipline).subscribe(res =>{
    this.departments.reset();
  })
}
}


ONcancelClick(){
  this.route.navigateByUrl('/homePage')
}





}

import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'app-role-creation',
  templateUrl: './role-creation.component.html',
  styleUrls: ['./role-creation.component.css']
})
export class RoleCreationComponent {

RoleCreation : FormGroup | any 
RoleData:any 
constructor(private fb:FormBuilder,private auth:AuthService,private notify :NotificationService,private route :Router){}


ngOnInit(){
this.RoleCreation =this.fb.group({
  rolename :[''],
  roleDescription:['']
})
}

onSubmit(){

  const{rolename,roleDescription}=this.RoleCreation.value

if(rolename == null){
  this.notify.showError('Please Enter Role Name')
}else if(roleDescription == null){
  this.notify.showError('Please Enter Role Description')
}else if(rolename != null && roleDescription != null){
  this.auth.RoleCreation(rolename,roleDescription).subscribe(res=>{
  })
}
this.RoleCreation.reset();

}


onCancel(){
  sessionStorage.clear();
  this.route.navigateByUrl('/homePage')

}



RoleSubmit(){

if(this.RoleCreation.rolename == null){
this.notify.showError('Please Enter Role Name')
}else if(this.RoleCreation.roleDescription == null){
  this.notify.showError('Please Enter Role Description')
}

}

}

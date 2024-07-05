import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LoginDetails } from '../login-details';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  // UserDetails: UserDetails | any ;
 

  userDetails :any
  Name : string =''
role : number | undefined
  constructor(public userDataService :LoginDetails,private authService: AuthService,private route:ActivatedRoute,private notify:NotificationService,private router :Router) { }


  ngOnInit(): void {
    this.Name =this.userDataService._fullname 
    console.log(this.Name + ' this is _fullname ')
    console.log(this.userDataService + ' session data')
    this.userDataService._fullname = this.Name
    this.role = this.userDataService._roleid 
    console.log(this.role+' this is role at homepage')
    this.userDetails = JSON.parse(sessionStorage.getItem('Userdata')!);
    // this.userDetails = this.userDataService.getUserDetails();
  }

  
  
}


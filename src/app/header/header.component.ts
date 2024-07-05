import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDetails } from '../login-details';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  headerIcon : boolean = true
  currentUrl: string = '';
  UserDetails : any
  Name :string =''
  OtrStatus: any;
  EmailId : string |any
  showhidetooltip: boolean = false;
  roleid:any
  UserId : any

menuItems : { name :string; link :string; roles:string[]}[] =[
  { name :'HomePage',link :'/homepage',roles :['admin','Officer', ' Officer-1',' Officer-2', ' Officer-3',' Officer-4','Officer-5']},
  { name :'aboutus',link : '/aboutpage', roles : ['admin','Officer']},
  { name :'contactas',link : '/contactas', roles : ['admin','Officer-2']},
  { name :'pricing',link : '/pricing', roles : ['admin','Officer-1']}
]

  constructor(public Route : Router,public logindetails :LoginDetails,private notify: NotificationService){}
  @ViewChild("hoverBtn")
  hoverBtn!: ElementRef;
  isOpen = false;
 
  ngOnInit(): void {
    this.roleid = this.logindetails._roleid
    this.EmailId =this.logindetails._emailID
    this.UserId =this.logindetails._employeeID
    this.currentUrl = this.Route.url;

    if (this.currentUrl === '/UserRegister') {

    }else if(this.currentUrl === '/LoginPage'){
      }
     else {
      this.headerIcon = false;
    this.Inhome();
   
    }
    console.log('This is the  : ' + this.roleid)
     }

  SignupButton(){
      
    console.log(sessionStorage.getItem(this.logindetails.RegStatus) + " OTR STATS")
    if(this.OtrStatus==1){
      // this.notify.showError("You Already Login")
    }else{
      this.Route.navigateByUrl('/LoginPage');
    }
  }
 Inhome(){

  this.Name=this.logindetails._fullname

  this.OtrStatus = this.logindetails._RegStatus
  
 }
 toggleCard() {
  this.isOpen = !this.isOpen;
}
changePassword(){
  this.Route.navigateByUrl('/ChangePassword')
}

  mouseoverfunction() {
    console.log(' Mouse Over ..');
    if( this.OtrStatus == 1){   
    this.showhidetooltip = true;
    }
  }
  mouseoutfunction() {
    console.log(' Mouse Out ..');
    this.showhidetooltip = false;
  }
  mouseinfunction() {
    console.log(' Mouse In ..');
    this.showhidetooltip = true;
  }

  OnPricing(){
    this.Route.navigateByUrl('/Role')
  }



logout() {
  sessionStorage.clear();
    this.Route.navigateByUrl('/LoginPage');
}
}
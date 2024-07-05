import { DatePipe } from '@angular/common';
import { Component, HostListener } from '@angular/core';
import { RouterModule,Router } from '@angular/router';
import { LoginDetails } from './login-details';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from './auth.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[DatePipe],
  animations: [
    trigger('flyInOut', [
      state('in', style({ transform: 'translateX(0)' })),
      transition('void => *', [
        style({ transform: 'translateX(-100%)' }),
        animate(300)
      ]),
      transition('* => void', [
        animate(300, style({ transform: 'translateX(100%)' }))
      ])
    ])
  ]
})

export class AppComponent {
  username: any;


  constructor(private authService :AuthService,private Route: Router,   public todayDate:DatePipe,public global:LoginDetails,private toaster : ToastrService) {
    let currentDate = this.todayDate.transform((new Date),'MM/dd/yyyy')
console.log(currentDate)
const userDetails = this.global.getUserDetailsFromLocalStorage();

if (userDetails) {
  this.username = userDetails.username;
} else {
}
   }

   ngOnInit() {
    
    console.log(" Activated  ...")
    if (sessionStorage.getItem('UserId') != null) {
      this.global._employeeID = sessionStorage.getItem('UserId')
      this.global._emailID = sessionStorage.getItem('email')
      this.global._fullname = sessionStorage.getItem('username')
      this.global._mobile = sessionStorage.getItem('mobile')
      this.global._RegStatus=sessionStorage.getItem('RegStatus')
      this.global._roleid=sessionStorage.getItem('roleid')
      console.log(sessionStorage.getItem('roleid') + '  this -----------')
    }else if (sessionStorage.getItem('UserId') == null){
      // sessionStorage.clear()
      this.router.navigateByUrl('/login')
    }
    else {
      // sessionStorage.clear()
      this.router.navigateByUrl('/login')
    }
  }
  showError(message: string) {
    this.toaster.error(message);   
  }

  showSuccess(message : string)
  {
    this.toaster.success(message);
  }


  get dateToDisply(){
    return 
  }

 
  @HostListener('window:unload', ['$event'])
  beforeUnloadHandler(event: any) {
    this.myFunction(event)
    event.preventDefault();
    return false;
  }
  unloadHandler(event: any) {
    this.myFunction(event)
    // ...
  }

   myFunction(event: any) {
    console.log("this event come form: ", event)

   // this.cookies.deleteAll()


   window.onunload = function(){
    alert("The window is closing now!");
}
  


  }

  title = 'METRO POLIES';
router = this.Route
}
  
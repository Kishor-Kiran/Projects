import { BehaviorSubject } from "rxjs";
import { UserDetails } from "./auth.service";

export class LoginDetails {

   
    constructor() {}

    public UserId: string = "";
    public username: string = "";
    public email: string = "";
    public mobile: string = "";
    public RegStatus :any ;
    public roleid: any;


  public get _roleid(){
    return this.roleid;
  }
  public set _roleid(value: any) {
    this.roleid = value;
  }
  
    public get _employeeID() {
      return this.UserId;
    }
  
    public set _employeeID(Userid: any) {
      this.UserId = Userid;
    }
  
    public get _fullname() {
      return this.username;
    }
  
    public set _fullname(fullname: any) {
      this.username = fullname;
    }
  
    public get _emailID() {
      return this.email;
    }
  
    public set _emailID(email: any) {
      this.email = email;
    }
  
    public get _mobile() {
      return this.mobile;
    }
  
    public set _mobile(mobile: any) {
      this.mobile = mobile;
    }

    public get _RegStatus() {
        return this.RegStatus;
      }
    
      public set _RegStatus(RegStatus: any) {
        this.RegStatus = RegStatus;
      }

  

    private _userDetails = new BehaviorSubject<any>(null);
    userDetails$ = this._userDetails.asObservable();
  

  
    setUserDetails(userDetails: UserDetails) {
      this._userDetails.next(userDetails);
    }

    getUserDetails(): UserDetails | any {
    
        return this._userDetails.getValue();
      }

      getUserDetailsFromLocalStorage() {
        const userDetails = localStorage.getItem('userDetails');
        if (userDetails) {
          return JSON.parse(userDetails);
        } else {
          return null;
        }
      }
}

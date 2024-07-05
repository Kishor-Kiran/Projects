import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { LoginDetails } from './login-details';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
responseStatus : any
  private apiUrl = 'http://localhost:8087/FORUM';


  constructor(private http: HttpClient,private userDataService : LoginDetails) {


   }
   department( designationName :string,
    designationCode:string,
    department:string,
    descipline:string){
      return this.http.post(`${this.apiUrl}/Departments`,{designationName,designationCode,department,descipline})
   }


  Register(name: string , email: string,mobileNumber :string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/savepersonaldetails`, { name, email, mobileNumber,password });
  }
  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, {email, password})
      .pipe(
        map(response => {       
          localStorage.setItem('currentUser', JSON.stringify(response));
    
          return response;
      }));
  }


  UpdatePassword(Email : String,Password : string):Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/Password`,{
       Email,Password
  })
  }

  RoleCreation(rolename:string,roleDescription:string){
 return this.http.post<any>(`${this.apiUrl}/roleMaster`,{
  rolename,roleDescription
    
    })
  }


  roles(RoleDetails: any):Observable<any>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.apiUrl}/role`,RoleDetails,{ headers: headers })
  }

  registration(Registration : any):Observable<any>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  return this.http.post<any>(`${this.apiUrl}/PatientRegister`,Registration,{ headers: headers })
  }

  fetchRole(){
    var url :string =this.apiUrl+'/fetchRoles'
    return this.http.get(url);
  }

  fetchUserName(){
    var url:string =this.apiUrl+'/fetchUserName'
    return this.http.get(url)
  }
 
  }

  
export interface  UserDetails{
  name:string,
  email:string,
  password:string,
  RegStatus : number,
  roleid : number
  id: number,
  mobileNumber:number
}

export interface departments{
  designationName :string,
  designationCode:string,
  Department:string,
  descipline:string
}

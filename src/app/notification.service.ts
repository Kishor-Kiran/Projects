import { Injectable, OnInit } from '@angular/core';
import {ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationService implements OnInit {

  constructor(private toaster : ToastrService) { }
  ngOnInit(): void {
    
  }

  showError(message: string) {
    this.toaster.error(message);   
  }

  showSuccess(message : string)
  {
    this.toaster.success(message);
  }


}

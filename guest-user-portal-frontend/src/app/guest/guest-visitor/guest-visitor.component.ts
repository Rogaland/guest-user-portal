import {FormData} from '../form-data';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { FormDataService } from '../form-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guest-visitor',
  templateUrl: './guest-visitor.component.html',
  styleUrls: ['./guest-visitor.component.css']
})
export class GuestVisitorComponent {

  constructor(private formDataService: FormDataService,
    private router: Router) { }

  get formData(): FormData {
    return this.formDataService.getData();
  }

  next() {
    this.router.navigate(['/host']);
  }
}

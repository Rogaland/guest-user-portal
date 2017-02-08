import {FormData} from '../form-data';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormDataService } from '../form-data.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-guest-confirmation',
  templateUrl: './guest-confirmation.component.html',
  styleUrls: ['./guest-confirmation.component.css']
})
export class GuestConfirmationComponent implements OnDestroy, OnInit {

  constructor(private formDataService: FormDataService,
    private router: Router,
    private route: ActivatedRoute) { }

  get confirmationResult() {
    return this.formDataService.confirmationResult;
  }

  get formData(): FormData {
    return this.formDataService.getData();
  }

  ngOnDestroy() {
    this.formDataService.clearData();
  }

  next() {
    this.formDataService.clearData();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
    const result = this.confirmationResult;
    if (result.timeout) {
      window.setTimeout(() => this.next(), result.timeout * 1000);
    }
  }
}

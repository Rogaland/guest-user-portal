import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { FormDataService } from '../form-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guest-visitor',
  templateUrl: './guest-visitor.component.html',
  styleUrls: ['./guest-visitor.component.css']
})
export class GuestVisitorComponent implements OnInit, OnDestroy {

  @Input() formData;

  constructor(private formDataService: FormDataService,
              private router: Router) { }

  ngOnInit() {
    this.formData = this.formDataService.getData();
  }

  ngOnDestroy() {
    this.formDataService.setData(this.formData);
  }

  next() {
    console.log('go next');
    this.router.navigate(['/host']);
  }
}

import {FormData} from '../form-data';
import { error } from 'util';
import { EmployeeSearch } from 'app/shared/employee-search';
import { Component } from '@angular/core';
import { FormDataService } from '../form-data.service';
import { EmployeeSearchService } from 'app/shared/employee-search.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guest-host',
  templateUrl: './guest-host.component.html',
  styleUrls: ['./guest-host.component.css']
})
export class GuestHostComponent {

  private visiting: EmployeeSearch[];
  private searchField: string;
  private errorMessage: string;

  constructor(private formDataService: FormDataService,
    private employeeSearchService: EmployeeSearchService,
    private router: Router) { }

    get formData(): FormData {
    return this.formDataService.getData();
  }

  save() {
    this.formDataService.create().subscribe(result => {
      this.formDataService.confirmationResult = result;
      this.router.navigate(['/confirmation']);
    },
      err => {
        const body = JSON.parse(err._body);
        if (err.status === 302) {
          this.formDataService.confirmationResult = body;
          // User is registered already. Proceed to confirmation page.
          this.router.navigate(['/confirmation']);
        }
        this.errorMessage = body.message;
      });
  }

  search() {
    if (this.searchField && this.searchField.length > 3) {
      this.employeeSearchService.findUsers(this.searchField).subscribe(result => this.visiting = result);
    }
  }

  selected(dn: EmployeeSearch) {
    this.formData.owner = dn.dn;
  }
}

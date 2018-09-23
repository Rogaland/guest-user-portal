import {GuestUser} from '../guest-user';
import { error } from 'util';
import { EmployeeSearch } from 'app/shared/employee-search';
import { Component } from '@angular/core';
import { GuestUserService } from '../guest-user.service';
import { EmployeeSearchService } from 'app/shared/employee-search.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guest-host',
  templateUrl: './guest-host.component.html',
  styleUrls: ['./guest-host.component.css']
})
export class GuestHostComponent {

  public visiting: EmployeeSearch[];
  public searchField: string;
  public errorMessage: string;

  constructor(private guestUserService: GuestUserService,
    private employeeSearchService: EmployeeSearchService,
    private router: Router) { }

  get guestUser(): GuestUser {
    return this.guestUserService.getData();
  }

  save() {
    this.guestUserService.create(true, true, null).subscribe(result => {
      this.guestUserService.confirmationResult = result;
      this.router.navigate(['/confirmation']);
    },
      err => {
        const body = JSON.parse(err._body);
        if (err.status === 302) {
          this.guestUserService.confirmationResult = body;
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
    this.guestUser.owner = dn.dn;
  }
}

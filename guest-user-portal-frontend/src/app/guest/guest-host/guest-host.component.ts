import { ConfigService } from '../../config.service';
import { PrintService } from '../print/print.service';
import {GuestUser} from '../guest-user';
import { error } from 'util';
import { EmployeeSearch } from 'app/shared/employee-search';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { GuestUserService } from '../guest-user.service';
import { EmployeeSearchService } from 'app/shared/employee-search.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guest-host',
  templateUrl: './guest-host.component.html',
  styleUrls: ['./guest-host.component.css']
})
export class GuestHostComponent {

  private isLoading: boolean;
  private visiting: EmployeeSearch[];
  private searchField: string;
  private errorMessage: string;

  constructor(private guestUserService: GuestUserService,
    private employeeSearchService: EmployeeSearchService,
    private router: Router,
    private printService: PrintService,
    private config: ConfigService) { }

  get guestUser(): GuestUser {
    return this.guestUserService.getData();
  }

  save() {
    this.isLoading = true;
    this.guestUser.location = this.config.physicalLocation;

    this.guestUserService.create(true, true, null).subscribe(result => {
      this.guestUserService.confirmationResult = result;
      this.printLabel();
      this.isLoading = false;
      this.router.navigate(['/confirmation']);
    },
      err => {
        const body = JSON.parse(err._body);
        if (err.status === 302) {
          this.printLabel();
          this.guestUserService.confirmationResult = body;
          // User is registered already. Proceed to confirmation page.
          this.router.navigate(['/confirmation']);
        }
        this.errorMessage = body.message;
        this.isLoading = false;
      });
  }

  printLabel(): void {
    this.printService.printGuest(this.guestUser.firstName, this.guestUser.lastName, this.guestUser.organization);
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

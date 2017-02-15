import { EmployeeSearchService } from '../../shared/employee-search.service';
import { EmployeeSearch } from '../../shared/employee-search';
import { GuestUserService } from '../../guest/guest-user.service';
import { GuestUserAdminService } from '../guest-user-admin.service';
import { Router } from '@angular/router';
import { GuestUser } from '../../guest/guest-user';
import { Component } from '@angular/core';

@Component({
  selector: 'app-guest-registration',
  templateUrl: './guest-registration.component.html',
  styleUrls: ['./guest-registration.component.css']
})
export class GuestRegistrationComponent {

  private visiting: EmployeeSearch[];
  private searchField: string;
  private today: Date = new Date();
  private notifyHost: boolean = false;

  guestUser: GuestUser;

  constructor(private router: Router,
    private guestUserAdminService: GuestUserAdminService,
    private guestUserService: GuestUserService,
    private employeeSearchService: EmployeeSearchService) {
    this.guestUser = new GuestUser();
  }

  register() {
    this.guestUserService.create(this.notifyHost, true, this.guestUser).subscribe(result => this.router.navigate(['/admin']),
      err => {
        const body = JSON.parse(err._body);
        if (err.status === 302) {
          // User is registered already. Proceed to confirmation page.
          this.router.navigate(['/admin']);
        }
      });
  }

  cancel(): void {
    this.router.navigate(['/admin']);
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

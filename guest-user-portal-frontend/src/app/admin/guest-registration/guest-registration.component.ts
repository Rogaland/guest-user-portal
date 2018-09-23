import {EmployeeSearchService} from '../../shared/employee-search.service';
import {EmployeeSearch} from '../../shared/employee-search';
import {GuestUserService} from '../../guest/guest-user.service';
import {GuestUserAdminService} from '../guest-user-admin.service';
import {Router} from '@angular/router';
import {GuestUser} from '../../guest/guest-user';
import {Component} from '@angular/core';
import {IMyDateModel, IMyDpOptions, IMyDate} from 'mydatepicker';


@Component({
  selector: 'app-guest-registration',
  templateUrl: './guest-registration.component.html',
  styleUrls: ['./guest-registration.component.css']
})
export class GuestRegistrationComponent {

  public visiting: EmployeeSearch[];
  public searchField: string;
  public today: any = GuestRegistrationComponent.getToday();
  public notifyHost: boolean = false;

  public myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd.mm.yyyy',
    markCurrentDay: true,
    disableUntil: GuestRegistrationComponent.getDisableUntil(),
    disableSince: GuestRegistrationComponent.getDisableSince(),
  };

  guestUser: GuestUser;

  constructor(private router: Router,
              private guestUserAdminService: GuestUserAdminService,
              private guestUserService: GuestUserService,
              private employeeSearchService: EmployeeSearchService) {
    this.guestUser = new GuestUser();
  }

  static getDisableUntil(): IMyDate {
    const today = new Date();
    return {
      year: today.getFullYear(),
      month: today.getMonth() + 1,
      day: today.getDate() -1,
    }
  }

  static getDisableSince(): IMyDate {
    const today = new Date();
    return {
      year: today.getFullYear(),
      month: today.getMonth() + 1,
      day: today.getDate() + 8,
    }
  }

  static getToday(): any {
    const today = new Date();
    return { date: {
      year: today.getFullYear(),
      month: today.getMonth() + 1,
      day: today.getDate(),
    }}
  }


  onDateChanged(event: IMyDateModel) {
    const jsDate = event.jsdate;
    this.guestUser.dateOfVisit = jsDate.toLocaleDateString(); //`${event.date.year}${event.date.month}${event.date.day}`;
  }


  register() {
    console.log(this.guestUser);

    this.guestUserService.create(this.notifyHost, true, this.guestUser).subscribe(result => this.router.navigate(['/admin']),
      err => {
        const body = JSON.parse(err._body);
        if (err.status === 302) {
          // User is registered already. Proceed to confirmation page.
          this.router.navigate(['/admin'], {queryParams: {alreadyRegistered: true}});
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

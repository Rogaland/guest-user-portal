import { GuestUserAdminService } from '../guest-user-admin.service';
import { GuestUserAdmin } from '../guest-user-admin';
import { Component, EventEmitter, OnInit, Output, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-guest-today',
  templateUrl: './guest-today.component.html',
  styleUrls: ['./guest-today.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GuestTodayComponent implements OnInit {

  private guests: GuestUserAdmin[];
  constructor(private guestUserAdminService: GuestUserAdminService) { }

  get visitors(): GuestUserAdmin[] {
    return this.guests;
  }

  loadTodaysGuests() {
    this.guestUserAdminService.getTodaysUsers().subscribe(result => {
      this.guests = result;
    });
  }

  ngOnInit() {
    this.loadTodaysGuests();
    this.guestUserAdminService.dataManipulated.subscribe(() => this.loadTodaysGuests());
  }
}

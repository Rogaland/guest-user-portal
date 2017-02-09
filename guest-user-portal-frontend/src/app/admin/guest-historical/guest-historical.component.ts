import { GuestUserAdminService } from '../guest-user-admin.service';
import { GuestUserAdmin } from '../guest-user-admin';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-guest-historical',
  templateUrl: './guest-historical.component.html',
  styleUrls: ['./guest-historical.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GuestHistoricalComponent implements OnInit {

  private guests: GuestUserAdmin[];
  constructor(private guestUserAdminService: GuestUserAdminService) { }

  get visitors(): GuestUserAdmin[] {
    return this.guests;
  }

  loadHistoricalGuests() {
    this.guestUserAdminService.getHistoricalUsers().subscribe(result => this.guests = result);
  }

  ngOnInit() {
    this.loadHistoricalGuests();
  }
}

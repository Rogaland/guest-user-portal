import { ActivatedRoute, Router } from '@angular/router';
import { GuestUserAdminService } from '../guest-user-admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent implements OnInit {

  constructor(private guestUserAdminService: GuestUserAdminService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
  }

  get guests() {
    return this.guestUserAdminService.guests;
  }

  get guestsHistorical() {
    return this.guestUserAdminService.guestsHistorical;
  }

  archiveGuests(): void {
    this.guestUserAdminService.historizeGuests().subscribe();
  }

  archiveAllGuests(): void {
    if (confirm('Arkiver absolutt alle gjester?')) { // todo make pretty
      this.guestUserAdminService.historizeAllGuests().subscribe();
    }
  }
}

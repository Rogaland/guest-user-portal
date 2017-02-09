import { GuestUserService } from '../guest-user.service';
import { Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector: 'app-guest-navbar',
  templateUrl: './guest-navbar.component.html',
  styleUrls: ['./guest-navbar.component.css']
})
export class GuestNavbarComponent {

  constructor(private router: Router, private guestUserService: GuestUserService) { }

  abort() {
    this.guestUserService.clearData();
    this.router.navigate(['/']);
  }
}

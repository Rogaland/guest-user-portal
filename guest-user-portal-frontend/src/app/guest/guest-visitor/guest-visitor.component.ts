import {GuestUser} from '../guest-user';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { GuestUserService } from '../guest-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guest-visitor',
  templateUrl: './guest-visitor.component.html',
  styleUrls: ['./guest-visitor.component.css']
})
export class GuestVisitorComponent {

  constructor(private guestUserService: GuestUserService,
    private router: Router) { }

  get guestUser(): GuestUser {
    return this.guestUserService.getData();
  }

  next() {
    this.router.navigate(['/host']);
  }
}

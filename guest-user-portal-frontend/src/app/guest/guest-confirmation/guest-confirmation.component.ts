import {GuestUser} from '../guest-user';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { GuestUserService } from '../guest-user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-guest-confirmation',
  templateUrl: './guest-confirmation.component.html',
  styleUrls: ['./guest-confirmation.component.css']
})
export class GuestConfirmationComponent implements OnDestroy, OnInit {

  constructor(private guestUserService: GuestUserService,
    private router: Router,
    private route: ActivatedRoute) { }

  get confirmationResult() {
    return this.guestUserService.confirmationResult || {};
  }

  get guestUser(): GuestUser {
    return this.guestUserService.getData();
  }

  ngOnDestroy() {
    this.guestUserService.clearData();
  }

  next() {
    this.guestUserService.clearData();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
    const result = this.confirmationResult;
    if (result && result.timeout) {
      window.setTimeout(() => this.next(), result.timeout * 1000);
    }
  }
}

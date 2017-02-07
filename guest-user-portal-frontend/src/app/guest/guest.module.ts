import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GuestRoutingModule } from './guest-routing.module';
import { GuestComponent } from './guest.component';
import { GuestNavbarComponent } from './guest-navbar/guest-navbar.component';
import { GuestHostComponent } from './guest-host/guest-host.component';
import { GuestVisitorComponent } from './guest-visitor/guest-visitor.component';
import { GuestConfirmationComponent } from './guest-confirmation/guest-confirmation.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    GuestRoutingModule,
    FormsModule
  ],
  declarations: [GuestComponent, GuestVisitorComponent, GuestNavbarComponent, GuestHostComponent, GuestConfirmationComponent]
})
export class GuestModule { }

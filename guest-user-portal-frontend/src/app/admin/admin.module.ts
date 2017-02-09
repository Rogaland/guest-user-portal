import { FormsModule } from '@angular/forms';
import { GuestUserAdminService } from './guest-user-admin.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { UserTableComponent } from './user-table/user-table.component';
import { GuestTodayComponent } from './guest-today/guest-today.component';
import { GuestHistoricalComponent } from './guest-historical/guest-historical.component';
import { AdminNavbarComponent } from './admin-navbar/admin-navbar.component';
import { GuestRegistrationComponent } from './guest-registration/guest-registration.component';

@NgModule({
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ],
  declarations: [AdminComponent, UserTableComponent, UserTableComponent, GuestTodayComponent,
    GuestHistoricalComponent, AdminNavbarComponent, GuestRegistrationComponent],
  providers: [GuestUserAdminService]
})
export class AdminModule { }

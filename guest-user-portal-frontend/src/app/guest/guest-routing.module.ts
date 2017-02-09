import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GuestComponent } from './guest.component';
import { GuestVisitorComponent } from './guest-visitor/guest-visitor.component';
import { GuestHostComponent } from './guest-host/guest-host.component';
import { GuestConfirmationComponent } from './guest-confirmation/guest-confirmation.component';
import { EmployeeSearchService } from '../shared/employee-search.service';

const routes: Routes = [
  {
    path: '', component: GuestComponent,
    children: [
      { path: '', component: GuestVisitorComponent },
      { path: 'host', component: GuestHostComponent },
      { path: 'confirmation', component : GuestConfirmationComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [EmployeeSearchService]
})
export class GuestRoutingModule { }

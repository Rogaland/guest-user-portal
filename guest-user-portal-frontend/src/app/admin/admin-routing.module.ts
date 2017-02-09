import { AdminComponent } from './admin.component';
import { GuestTodayComponent } from './guest-today/guest-today.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GuestHistoricalComponent } from './guest-historical/guest-historical.component';
import { GuestRegistrationComponent } from './guest-registration/guest-registration.component';


const routes: Routes = [
  {
    path: 'admin', component: AdminComponent,
    children: [
      { path: '', component: GuestTodayComponent },
      { path: 'historical', component: GuestHistoricalComponent },
      { path: 'new', component: GuestRegistrationComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class AdminRoutingModule { }

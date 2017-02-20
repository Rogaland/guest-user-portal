import { AdminComponent } from './admin/admin.component';
import { AdminModule } from './admin/admin.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GuestModule } from './guest/guest.module';
import { GuestComponent } from './guest/guest.component';

const routes: Routes = [
  { path: '', component: GuestComponent },
  { path: 'admin', component: AdminComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    GuestModule,
    AdminModule,
    RouterModule.forRoot(routes, { useHash: true })
  ],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }

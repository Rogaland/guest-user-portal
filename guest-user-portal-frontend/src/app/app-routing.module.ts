import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GuestModule } from './guest/guest.module';
import { GuestComponent } from './guest/guest.component';

const routes: Routes = [
  { path: '', component : GuestComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    GuestModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }

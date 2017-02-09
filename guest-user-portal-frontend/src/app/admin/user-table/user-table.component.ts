import { GuestUserAdmin } from '../guest-user-admin';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  @Input() users: GuestUserAdmin;

  constructor() { }

  ngOnInit() {
  }

}

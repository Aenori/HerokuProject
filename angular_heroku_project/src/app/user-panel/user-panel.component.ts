import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {
  selectedUser?: User;

  constructor() { }

  ngOnInit(): void {
  }

  selectUser(user: User) {
    this.selectedUser = user;
  }
}

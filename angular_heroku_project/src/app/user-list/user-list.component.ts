import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../model/user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  selectedUser?: User;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getHeroes()
      .subscribe(users => this.users = users);
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }
}

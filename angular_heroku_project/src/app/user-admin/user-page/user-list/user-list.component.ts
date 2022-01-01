import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  selectedUser?: User;
  msg: string = "";

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getHeroes()
      .subscribe(users => {this.users = users; this.users.sort((a, b) => a.id - b.id);} );
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }

  onClickSubmit(data: any): void {
    this.userService.saveUser(data)
      .subscribe(user => this.msg = `User ${user.username} updated with success`);
  }

  onClickSubmitDelete(id: number): void {
    this.userService.deleteUser(id)
      .subscribe(data => {
        this.msg = `User ${id} deleted with success`;
        let removed: User = this.users.find(u => (u.id === id))!;
        this.users.splice(this.users.indexOf(removed), 1);
      },
        error => {this.msg = `User ${id} deletion failed !`;});
  }
}

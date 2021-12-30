import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {catchError, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userUrl = "http://localhost:8081/api/users";

  constructor(private http: HttpClient) { }

  getHeroes(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

  saveUser(data: any): Observable<User> {
    if(data.id) {
      return this.http.put<User>(`${(this.userUrl)}/${data.id}`, data);
    }
    return this.http.post<User>(this.userUrl, data);
  }

  deleteUser(id: number): Observable<Object> {
    return this.http.delete(`${(this.userUrl)}/${id}`);
  }
}

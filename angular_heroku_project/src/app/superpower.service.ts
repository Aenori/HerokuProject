import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Superpower} from "./model/superpower";

@Injectable({
  providedIn: 'root'
})
export class SuperpowerService {
  superpowerUrl = "http://localhost:8081/api/superpowers";

  constructor(private http: HttpClient) { }

  getSuperpowers() {
    return this.http.get<Superpower[]>(this.superpowerUrl);
  }
}

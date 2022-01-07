import {Component, Input, OnInit} from '@angular/core';
import {Superpower} from "../../model/superpower";

@Component({
  selector: 'app-superpower-list',
  templateUrl: './superpower-list.component.html',
  styleUrls: ['./superpower-list.component.css']
})
export class SuperpowerListComponent implements OnInit {
  @Input() superpowers: Superpower[] = [];
  selectedsuperpower?: Superpower;

  constructor() { }

  ngOnInit(): void {
  }

  onSelect(superpower: Superpower) {

  }
}

import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {SuperpowerService} from "../superpower.service";
import {Superpower} from "../model/superpower";

@Component({
  selector: 'app-superpower-panel',
  templateUrl: './superpower-panel.component.html',
  styleUrls: ['./superpower-panel.component.css']
})
export class SuperpowerPanelComponent implements OnInit {
  superpowers: Superpower[] = [];

  constructor(private superposerService: SuperpowerService) { }

  ngOnInit(): void {
    this.retrieveSuperpowers();
  }

  private retrieveSuperpowers() {
    this.superposerService.getSuperpowers()
      .subscribe(superpowers => {this.superpowers = superpowers; this.superpowers.sort((a, b) => a.id - b.id);} );
  }
}

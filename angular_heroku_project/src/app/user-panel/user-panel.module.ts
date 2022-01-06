import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserPanelRoutingModule } from './user-panel-routing.module';
import { UserPanelComponent } from './user-panel.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserCardComponent } from './user-card/user-card.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    UserPanelComponent,
    UserListComponent,
    UserFormComponent,
    UserCardComponent
  ],
  imports: [
    CommonModule,
    UserPanelRoutingModule,
    FormsModule
  ]
})
export class UserPanelModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { UserFormComponent } from './user-panel/user-form/user-form.component';



@NgModule({
  declarations: [
    UserPanelComponent,
    UserFormComponent
  ],
  imports: [
    CommonModule
  ]
})
export class UserAdminModule { }

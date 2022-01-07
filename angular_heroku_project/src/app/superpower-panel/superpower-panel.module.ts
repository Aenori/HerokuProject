import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SuperpowerPanelRoutingModule } from './superpower-panel-routing.module';
import { SuperpowerPanelComponent } from './superpower-panel.component';
import { SuperpowerListComponent } from './superpower-list/superpower-list.component';
import { SuperpowerFormCreateComponent } from './superpower-form-create/superpower-form-create.component';
import { SuperpowerFormUpdateComponent } from './superpower-form-update/superpower-form-update.component';


@NgModule({
  declarations: [
    SuperpowerPanelComponent,
    SuperpowerListComponent,
    SuperpowerFormCreateComponent,
    SuperpowerFormUpdateComponent
  ],
  imports: [
    CommonModule,
    SuperpowerPanelRoutingModule
  ]
})
export class SuperpowerPanelModule { }

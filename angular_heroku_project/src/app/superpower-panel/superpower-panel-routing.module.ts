import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SuperpowerPanelComponent } from './superpower-panel.component';

const routes: Routes = [{ path: '', component: SuperpowerPanelComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SuperpowerPanelRoutingModule { }

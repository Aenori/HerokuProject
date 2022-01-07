import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: 'users', loadChildren: () => import('./user-panel/user-panel.module').then(m => m.UserPanelModule) },
{ path: 'test', loadChildren: () => import('./test/test.module').then(m => m.TestModule) },
{ path: 'superpowers', loadChildren: () => import('./superpower-panel/superpower-panel.module').then(m => m.SuperpowerPanelModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

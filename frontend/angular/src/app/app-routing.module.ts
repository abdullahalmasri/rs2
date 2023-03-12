import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponentComponent } from './login-component/login-component.component';
import { PageDataComponent } from './page-data/page-data.component';


const routes: Routes = [
  {
    path:'pageData',
    component:PageDataComponent
  },
  {
    path:'',
    component:LoginComponentComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

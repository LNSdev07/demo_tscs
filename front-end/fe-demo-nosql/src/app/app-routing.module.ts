import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserManagementComponent } from './user-management/user-management.component';

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'signup', component: SignUpComponent},
  {path: 'user-management', component: UserManagementComponent},
  {path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

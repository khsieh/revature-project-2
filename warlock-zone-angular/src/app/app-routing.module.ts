import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
//components
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { FeedComponent } from './components/feed/feed.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from "./services/auth-guard/auth.guard";

const routes: Routes = [
    {path:'login',component:LoginComponent},
    {path:'',component:LoginComponent},
    {path:'home',component:HomeComponent, canActivate:[AuthGuard]},
    {path:'register', component:RegisterComponent},
    {path:'editProfile',component:EditProfileComponent, canActivate:[AuthGuard]},
    {path:'**', component:ErrorPageComponent} //change this to a 404 error page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

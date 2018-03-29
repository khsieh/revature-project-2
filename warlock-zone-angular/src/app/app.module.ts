import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule } from '@angular/http';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

//components
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { FeedComponent } from './components/feed/feed.component';
import { PostComponent } from './components/post/post.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { AlertComponent } from './components/alert/alert.component';
import { PwEmailComponent } from './components/pw-email/pw-email.component';
import { PwResetComponent } from './components/pw-reset/pw-reset.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';

//services
import { LoginService } from './services/login/login.service';
import { PostsService } from './services/posts/posts.service';
import { ToggleNewPostService } from './services/util/toggle-new-post.service';
import { CurUserService } from './services/cache/curUser/cur-user.service';
import { LogoutService } from './services/logout/logout.service';
import { AuthGuard } from './services/auth-guard/auth.guard';
import { AuthTokenService } from './services/cache/authToken/auth-token.service';
import { RegisterService } from './services/register/register.service';
import { UpdateUserService } from './services/update-user/update-user.service';
import { ValidateService } from './services/validate/validate.service';
import { AlertService } from './services/alert/alert.service';
import { ResetPwService } from './services/reset-pw/reset-pw.service';



@NgModule({
  //components
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    FeedComponent,
    PostComponent,
    HomeComponent,
    ErrorPageComponent,
    EditProfileComponent,
    RegisterComponent,
    AlertComponent,
    PwEmailComponent,
    PwResetComponent
  ],
  //modules, mostly from ng core
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    BrowserAnimationsModule,
    NgbModule.forRoot(),
    HttpModule,
    HttpClientModule,
    //separated routing
    AppRoutingModule
  ],
  //services, helper methods to handle connectivity to back end
  providers: [
    LoginService,
    PostsService,
    ToggleNewPostService,
    CurUserService,
    LogoutService,
    AuthGuard,
    AuthTokenService,
    RegisterService,
    UpdateUserService,
    ValidateService,
    AlertService,
    ResetPwService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

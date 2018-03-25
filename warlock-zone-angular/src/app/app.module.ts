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
import { LogoutComponent } from './components/logout/logout.component';
import { FeedComponent } from './components/feed/feed.component';
import { PostComponent } from './components/post/post.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';

//services
import { LoginService } from './services/login/login.service';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { PostsService } from './services/posts/posts.service';
import { ToggleNewPostService } from './services/util/toggle-new-post.service';



@NgModule({
  //components
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    LogoutComponent,
    FeedComponent,
    PostComponent,
    HomeComponent,
    ErrorPageComponent,
    EditProfileComponent,
    RegisterComponent
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
    ToggleNewPostService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

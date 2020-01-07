import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BackbuttonComponent } from './backbutton/backbutton.component';
import { ForgetpasswordComponent } from './forgetpassword/forgetpassword.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PromotionsComponent } from './promotions/promotions.component';
import { SignupComponent } from './signup/signup.component';
import { TagPageComponent } from './tag-page/tag-page.component';

@NgModule({
  declarations: [
    AppComponent,
    BackbuttonComponent,
    ForgetpasswordComponent,
    HomepageComponent,
    LoginComponent,
    LogoutComponent,
    PromotionsComponent,
    SignupComponent,
    TagPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path: '', component: LoginComponent },
      { path: 'homepage', component: HomepageComponent },
      { path: 'login', component: LoginComponent },
      { path: 'tagpage', component: TagPageComponent },
      { path: 'logout', component: LogoutComponent },
      { path: 'promotions', component: PromotionsComponent },
      { path: 'signup', component: SignupComponent },
      { path: 'forgetpassword', component: ForgetpasswordComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { TagPageComponent } from './tag-page/tag-page.component';
import { LogoutComponent } from './logout/logout.component';
import { PromotionsComponent } from './promotions/promotions.component';
import { SignupComponent } from './signup/signup.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path: '', component: LoginComponent },
      { path: '/homepage', component: HomepageComponent },
      { path: '/tagpage', component: TagPageComponent },
      { path: '/logout', component: LogoutComponent },
      { path: '/promotions', component: PromotionsComponent },
      { path: '/signup', component: SignupComponent },
      { path: '/forgetpassword', component: ForgetPasswordComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

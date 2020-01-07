
import { ApiRequestService } from './../api-request.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  result = '';

  constructor(private apiRequests: ApiRequestService, private router: Router) { }

  ngOnInit() {
  }

  async login(username, password) {
    let body = {
      username: username,
      password: password
    }

    await this.apiRequests.login('customers', body).subscribe(res => {
      this.result = res;
      if (this.result == 'Invalid Username/Password') {
        this.result = res;
        console.log("res");
      } else {
        this.router.navigate(['homepage']);
      }
    });
  }


}

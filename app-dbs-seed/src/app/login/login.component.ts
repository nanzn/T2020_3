import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
// Define variable here

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    // Subscribe to parameters here
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
// Define variable here

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    // Subscribe to parameters here
  }

}

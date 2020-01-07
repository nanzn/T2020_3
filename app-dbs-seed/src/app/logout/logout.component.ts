import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
// Define variable here

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    // Subscribe to parameters here
  }

}

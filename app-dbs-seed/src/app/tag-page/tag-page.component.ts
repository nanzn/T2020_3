import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tag-page',
  templateUrl: './tag-page.component.html',
  styleUrls: ['./tag-page.component.css']
})
export class TagPageComponent implements OnInit {
// Define variable here

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    // Subscribe to parameters here
  }

}

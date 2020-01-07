import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Input } from '@angular/core';

@Component({
  selector: 'app-backbutton',
  template: `<button mat-button (click)="goBack()">Back</button>`,
})
export class BackbuttonComponent implements OnInit {
  @Input()color: string;

  constructor(
    private location: Location
  ) { }

  goBack() {
    this.location.back();
  }

  ngOnInit() {
  }

}

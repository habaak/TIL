import { Component, ViewChild } from '@angular/core';
import { NavController, Content } from 'ionic-angular';

@Component({
  selector: 'page-bus',
  templateUrl: 'bus.html'
})
export class BusPage {
  @ViewChild(Content) content: Content;
  public like_btn = {
    color:'black',
    icon_name:'heart-outline'
  };

  public tap:number = 0;
  constructor(public navCtrl: NavController) {
    
  }
  likeButton() {
    if(this.like_btn.icon_name === 'heart-outline') {
      this.like_btn.icon_name = 'heart';
      this.like_btn.color = 'danger';
      // Do some API job in here for real!
    }
    else {
      this.like_btn.icon_name = 'heart-outline';
      this.like_btn.color = 'black';
    }
  }
}

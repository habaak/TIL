import { Component, ViewChild, ElementRef, Injectable } from '@angular/core';
import { NavController } from 'ionic-angular';
//import { Http, Response } from '@angular/http';
/**
 * Generated class for the MapPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
declare var google : any;
@Component({
  selector: 'page-map',
  templateUrl: 'map.html',
})
export class MapPage {

  @ViewChild('map') mapRef: ElementRef;
  
  constructor(public navCtrl: NavController) {
  }

  ionViewDidLoad() {
    console.log(this.mapRef);
    this.showMap();
  }
  
  showMap() {
    //Location - lat, lon
    const location = new google.maps.LatLng(37.4973317,127.0310834);
    //Map options
    const options = {
      center:location,
      zoom:17,
      streetViewControl:false
    }
    const map = new google.maps.Map(this.mapRef.nativeElement, options);
    
    // setTimeout(()=>{
    //   this.addMarker(Position,map);
    // },3000);
    
    this.addMarker(location, map);
  }

  

  addMarker(position,map){
    return new google.maps.Marker({
      position,
      map
    });
  }
}


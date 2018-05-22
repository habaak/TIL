import { Component } from '@angular/core';

import { IonicPage, NavController, NavParams, ViewController, LoadingController, AlertController } from 'ionic-angular';
import * as firebase from 'firebase';
@Component({
  selector: 'page-my',
  templateUrl: 'my.html'
})
export class MyPage {

  public user_data = {
    profile_img: 'https://avatars1.githubusercontent.com/u/918975?v=3&s=120',
    name_surname: 'HABAAK',
    username: '박준하',
    website: 'https://github.com/candelibas',
    description: 'Software developer, open-source lover & Invoker spammer',
    email: 'candelibas00@gmail.com',
    phone: '',
    gender: 'male'
  }

  constructor(
    public navCtrl: NavController, 
    public navParams: NavParams, 
    public viewCtrl: ViewController,
    public loadingCtrl: LoadingController,
    public alertCtrl: AlertController
    ) {
  }
  logout() {
      let confirm = this.alertCtrl.create({
        title: 'Log out',
        message: '정말로 로그아웃 하시겠습니까?',
        buttons: [
          {
            text: '아니오',
            handler: () => {
              console.log('Disagree clicked');
            }
          },
          {
            text: '예',
            handler: () => {
              console.log('Agree clicked');
              firebase.auth().signOut().then(() => {
                // Sign-out successful.
                console.log("LogOut");
              }).catch(function(error) {
                // An error happened.
                console.log(error);
              });
            }
          }
        ]
      });
      confirm.present();
  }
}

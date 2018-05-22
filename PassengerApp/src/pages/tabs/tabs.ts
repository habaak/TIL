import { Component } from '@angular/core';

import { BusPage } from '../bus/bus';
import { MyPage } from '../my/my';
import { HomePage } from '../home/home';
import { MapPage } from '../map/map';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = MapPage;
  tab3Root = BusPage;
  tab4Root = MyPage;
  

  constructor() {

  }
}

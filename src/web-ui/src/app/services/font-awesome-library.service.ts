import { Injectable } from '@angular/core';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import {
  faBars, faCalendar,
  faChair,
  faDharmachakra,
  faFan,
  faGasPump,
  faLightbulb, faMobileScreen,
  faOilCan, faTrash,
  faWrench,
  faXmark
} from '@fortawesome/free-solid-svg-icons';

@Injectable({
  providedIn: 'root'
})
export class FontAwesomeLibraryService {
  constructor(private library: FaIconLibrary) {

  }

  setIcons() {
    this.library.addIcons(
      faFan,
      faLightbulb,
      faChair,
      faDharmachakra,
      faGasPump,
      faOilCan,
      faWrench,
      faBars,
      faXmark,
      faMobileScreen,
      faCalendar,
      faTrash
    );
  }
}

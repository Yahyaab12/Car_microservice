import { Component, Input } from '@angular/core';


@Component({
  selector: 'footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})

export class FooterComponent {
  year: number = new Date().getFullYear();
  @Input()
  isMobile = false;
}

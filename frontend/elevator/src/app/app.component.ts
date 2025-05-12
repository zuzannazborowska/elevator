import { Component } from '@angular/core';
import { BuildingComponent } from './components/building/building.component';

@Component({
  selector: 'app-root',
  imports: [BuildingComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'elevator';
}

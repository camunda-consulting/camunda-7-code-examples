import { Component } from '@angular/core';
import { MyProcessData } from '../../schemas/MyProcessData';
@Component({
  selector: 'startNewProcess',
  templateUrl: './startNewProcess.component.html',
  styleUrls: []
})
export class StartNewProcessComponent {
  submitted:boolean = false;
  model = new MyProcessData();

  onSubmit() {
    this.submitted = true;
  }

}

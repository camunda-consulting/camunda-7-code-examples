import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CamundaRestService } from '../../camunda-rest.service';
import { CompleteTaskComponent } from '../general/complete-task.component';
import { MyProcessData } from '../../schemas/MyProcessData';

@Component({
  selector: 'approveDataTask',
  templateUrl: './approveDataTask.component.html',
  styleUrls: []
})
export class approveDataTaskComponent extends CompleteTaskComponent {
  submitted:boolean = false;
  model = new MyProcessData('','',false);

  constructor(route: ActivatedRoute,
    router: Router,
    camundaRestService: CamundaRestService) {
    super(route, router, camundaRestService);
    this.route.params.subscribe(params => {
      const taskId = params['id'];
      const variableNames = Object.keys(this.model).join(',');
      this.loadExistingVariables(taskId, variableNames);
    });
  }

}

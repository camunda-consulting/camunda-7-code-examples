import { CamundaRestService } from '../../camunda-rest.service';
import { ActivatedRoute, Router } from '@angular/router';

export class CompleteTaskComponent {
  model
  submitted
  route: ActivatedRoute
  router: Router
  camundaRestService: CamundaRestService

  constructor(route: ActivatedRoute,
    router: Router,
    camundaRestService: CamundaRestService,
    ) {
      this.route = route;
      this.router = router;
      this.camundaRestService = camundaRestService;
  }
  onSubmit() {
    this.route.params.subscribe(params => {
      const taskId = params['id'];
      const variables = this.generateVariablesFromFormFields();
      this.camundaRestService.postCompleteTask(taskId, variables).subscribe();
      this.submitted = true;
      this.router.navigate(['/tasklist']);
    });
  }
  generateVariablesFromFormFields() {
    const variables = {
      variables: { }
    };
    Object.keys(this.model).forEach((field) => {
      variables.variables[field] = {
        value: this.model[field]
      };
    });

    return variables;
  }
}

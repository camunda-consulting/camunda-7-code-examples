import { CamundaRestService } from '../../camunda-rest.service';
import { ActivatedRoute } from '@angular/router';

export class StartProcessInstanceComponent {
  model
  submitted

  constructor(private route: ActivatedRoute,
    private camundaRestService: CamundaRestService,
    ) {

  }
  onSubmit() {
    this.route.params.subscribe(params => {
      const processDefinitionKey = params['processdefinitionkey'];
      const variables = this.generateVariablesFromFormFields();
      this.camundaRestService.postProcessInstance(processDefinitionKey, variables).subscribe();
      this.submitted = true;
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

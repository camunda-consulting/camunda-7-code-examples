import { TestBed, inject } from '@angular/core/testing';

import { CamundaRestService } from './camunda-rest.service';

describe('CamundaRestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CamundaRestService]
    });
  });

  it('should be created', inject([CamundaRestService], (service: CamundaRestService) => {
    expect(service).toBeTruthy();
  }));
});

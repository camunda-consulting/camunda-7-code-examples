import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcesslistComponent } from './processlist.component';

describe('ProcesslistComponent', () => {
  let component: ProcesslistComponent;
  let fixture: ComponentFixture<ProcesslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcesslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcesslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

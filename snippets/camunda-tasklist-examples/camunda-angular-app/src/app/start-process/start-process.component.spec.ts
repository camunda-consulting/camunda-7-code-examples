import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartProcessComponent } from './start-process.component';

describe('StartProcessComponent', () => {
  let component: StartProcessComponent;
  let fixture: ComponentFixture<StartProcessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartProcessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartProcessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

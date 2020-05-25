import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MotorBikesComponent } from './motor-bikes.component';

describe('MotorBikesComponent', () => {
  let component: MotorBikesComponent;
  let fixture: ComponentFixture<MotorBikesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MotorBikesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MotorBikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

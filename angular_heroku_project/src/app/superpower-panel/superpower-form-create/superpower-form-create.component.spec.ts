import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperpowerFormCreateComponent } from './superpower-form-create.component';

describe('SuperpowerFormCreateComponent', () => {
  let component: SuperpowerFormCreateComponent;
  let fixture: ComponentFixture<SuperpowerFormCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuperpowerFormCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperpowerFormCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

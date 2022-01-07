import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperpowerFormUpdateComponent } from './superpower-form-update.component';

describe('SuperpowerFormUpdateComponent', () => {
  let component: SuperpowerFormUpdateComponent;
  let fixture: ComponentFixture<SuperpowerFormUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuperpowerFormUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperpowerFormUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

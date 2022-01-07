import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperpowerListComponent } from './superpower-list.component';

describe('SuperpowerListComponent', () => {
  let component: SuperpowerListComponent;
  let fixture: ComponentFixture<SuperpowerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuperpowerListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperpowerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperpowerPanelComponent } from './superpower-panel.component';

describe('SuperpowerPanelComponent', () => {
  let component: SuperpowerPanelComponent;
  let fixture: ComponentFixture<SuperpowerPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuperpowerPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperpowerPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

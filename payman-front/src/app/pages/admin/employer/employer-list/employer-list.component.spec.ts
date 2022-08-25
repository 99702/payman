import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployerListComponent } from './employer-list.component';

describe('EmployerListComponent', () => {
  let component: EmployerListComponent;
  let fixture: ComponentFixture<EmployerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployerListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

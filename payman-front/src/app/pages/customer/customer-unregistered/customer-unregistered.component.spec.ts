import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerUnregisteredComponent } from './customer-unregistered.component';

describe('CustomerUnregisteredComponent', () => {
  let component: CustomerUnregisteredComponent;
  let fixture: ComponentFixture<CustomerUnregisteredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerUnregisteredComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerUnregisteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

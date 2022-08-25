import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAddAccountComponent } from './customer-add-account.component';

describe('CustomerAddAccountComponent', () => {
  let component: CustomerAddAccountComponent;
  let fixture: ComponentFixture<CustomerAddAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAddAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAddAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

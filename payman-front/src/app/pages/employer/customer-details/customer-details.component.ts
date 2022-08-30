import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerDetailDto } from 'src/app/dto/CustomerDetailDto';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
    selector: 'app-customer-details',
    templateUrl: './customer-details.component.html',
    styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {
    customerDetail: any = null;
    constructor(private customer: CustomerService, private activatedRoute: ActivatedRoute, private router: Router) {
    }

    ngOnInit(): void {
        if (this.activatedRoute.snapshot.paramMap.get("accNo") != null) {
            let accNo = this.activatedRoute.snapshot.paramMap.get("accNo");
            this.customer.getCustomerByAccountNumber(accNo).subscribe(
                (data: CustomerDetailDto) => {
                    this.customerDetail = data;
                },
                (error: any) => {
                }
            )
        }
    }
}

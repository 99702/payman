import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { CustomerListDto } from 'src/app/dto/CustomerListDto';
import { CustomerService } from 'src/app/services/customer.service';


@Component({
    selector: 'app-customer-list',
    templateUrl: './customer-list.component.html',
    styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
    customers = new Array<any>();
    displayedColumns: string[] = ['account_number', 'balance', 'citizenship_number', 'dob', 'gender', 'mobile', 'full_name'];
    constructor(private customer: CustomerService, private router: Router) {
        this.customer.getAllCustomer().subscribe(
            (data: CustomerListDto[]) => {
                this.customers = data;
            }
        )
    }

    ngOnInit(): void {


    }
    clicker(row: any) {
        this.router.navigate([`/employer/customer/registered/detail/${row.account_number}`]);
    }
}

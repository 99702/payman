import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
    selector: 'app-customer-unregistered',
    templateUrl: './customer-unregistered.component.html',
    styleUrls: ['./customer-unregistered.component.css']
})

export class CustomerUnregisteredComponent implements OnInit {
    customersUnregistered = new Array<any>();
    displayedColumns: string[] = ["citizenship_number", "mobile", "created_at", "dob", "gender", "full_name", "actions"]

    constructor(private customer: CustomerService, private router: Router) {
        this.customer.getAllCustomerUnregistered().subscribe(
            (data: any) => {
                console.log(data)
                this.customersUnregistered = data;
            }
        )
    }


    ngOnInit(): void {
    }

    clicker(row: any) {
        this.router.navigate([`/employer/customer/unregistered/${row.mobile}`]);
    }
}

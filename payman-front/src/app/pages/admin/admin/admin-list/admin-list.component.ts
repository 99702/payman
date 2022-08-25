import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
    selector: 'app-admin-list',
    templateUrl: './admin-list.component.html',
    styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit {
    admins = new Array<any>();
    displayedColumns: string[] = ["citizenship_number", "created_at", "dob", "gender", "full_name"]

    constructor(private customer: CustomerService) {
        this.customer.getAllAdmin().subscribe(
            (data: any) => {
                this.admins = data;
            }
        )
    }
    ngOnInit(): void {
    }
}

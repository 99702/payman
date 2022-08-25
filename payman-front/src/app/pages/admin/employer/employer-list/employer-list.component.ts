import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
    selector: 'app-employer-list',
    templateUrl: './employer-list.component.html',
    styleUrls: ['./employer-list.component.css']
})
export class EmployerListComponent implements OnInit {
    employers = new Array<any>();
    displayedColumns: string[] = ["citizenship_number", "created_at", "dob", "gender", "full_name"]

    constructor(private customer: CustomerService) {
        this.customer.getAllEmployer().subscribe(
            (data: any) => {
                this.employers = data;
                console.log(data);
            }
        )
    }

    ngOnInit(): void {
    }

}

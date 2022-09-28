import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { LoginService } from 'src/app/services/login.service';

@Component({
    selector: 'app-login-history',
    templateUrl: './login-history.component.html',
    styleUrls: ['./login-history.component.css'
    ]
})
export class LoginHistoryComponent implements OnInit {
    histories = null;
    isLoading = false;
    pageNo: number = 1;
    totalPageSize: number = 0;
    totalElements: number = 0;

    displayedColumns = ["IP", "Login_date"]
    constructor(private login: LoginService) { }
    ngOnInit(): void {
        this.loadData();
    }

    loadData() {
        this.isLoading = true;
        this.login.getLoginHistory(this.pageNo).subscribe(
            (data: any) => {
                this.histories = data;
                this.totalPageSize = data[0]?.totalPageSize;
                this.totalElements = data[0]?.totalElements;
                this.isLoading = false;
            },

            (error: any) => {
                console.log(error);
                this.isLoading = false;
            }
        )
    }
    pageChanged(event: PageEvent) {
        console.log(event.pageIndex + 1);
        this.pageNo = event.pageIndex + 1
        this.loadData();
    }
}

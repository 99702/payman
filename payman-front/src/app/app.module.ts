import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { EmployerDashboardComponent } from './pages/employer/employer-dashboard/employer-dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card'
import { MatInputModule } from '@angular/material/input'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button'
import { MatDividerModule } from '@angular/material/divider'
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material/core'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatProgressBarModule } from '@angular/material/progress-bar'
import { MatListModule } from '@angular/material/list'
import { MatIconModule } from '@angular/material/icon'
import { MatSelectModule } from '@angular/material/select'
import { MatSidenavModule } from '@angular/material/sidenav'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { authInterceptorProviders } from './services/auth.interceptor';
import { MainComponent } from './pages/admin/main/main.component';
import { EmployerListComponent } from './pages/admin/employer/employer-list/employer-list.component';
import { CustomerListComponent } from './pages/admin/customer/customer-list/customer-list.component';
import { AdminListComponent } from './pages/admin/admin/admin-list/admin-list.component';
import { CustomerDashboardComponent } from './pages/customer/customer-dashboard/customer-dashboard.component';
import { RegisterComponent } from './pages/register/register.component';
import { CustomerUnregisteredComponent } from './pages/customer/customer-unregistered/customer-unregistered.component';
import { CustomerAddAccountComponent } from './pages/employer/customer/customer-add-account/customer-add-account.component';
import { BalanceTransferComponent } from './pages/customer/balance-transfer/balance-transfer.component';
import { TransactionHistoryComponent } from './pages/customer/transaction-history/transaction-history.component';
import { LoginHistoryComponent } from './pages/customer/login-history/login-history.component';
import { DialogComponent } from './pages/customer/dialog/dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { CustomerDetailsComponent } from './pages/employer/customer-details/customer-details.component';
import { CustomerProfileComponent } from './pages/customer/customer-profile/customer-profile.component'

@NgModule({
    declarations: [
        AppComponent,
        FooterComponent,
        NavbarComponent,
        HomeComponent,
        LoginComponent,
        AdminDashboardComponent,
        EmployerDashboardComponent,
        MainComponent,
        EmployerListComponent,
        CustomerListComponent,
        AdminListComponent,
        CustomerDashboardComponent,
        RegisterComponent,
        CustomerUnregisteredComponent,
        CustomerAddAccountComponent,
        BalanceTransferComponent,
        TransactionHistoryComponent,
        LoginHistoryComponent,
        DialogComponent,
        CustomerDetailsComponent,
        CustomerProfileComponent,
    ],

    imports: [
        MatPaginatorModule,
        MatDialogModule,
        BrowserModule,
        MatTableModule,
        AppRoutingModule,
        MatCardModule,
        MatButtonModule,
        MatInputModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        MatToolbarModule,
        MatIconModule,
        HttpClientModule,
        MatDividerModule,
        MatSidenavModule,
        MatListModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatNativeDateModule,
        MatSelectModule,
        MatSnackBarModule,
        MatProgressBarModule,
    ],
    providers: [
        authInterceptorProviders,
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }

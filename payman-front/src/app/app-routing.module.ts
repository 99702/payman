import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { AdminListComponent } from './pages/admin/admin/admin-list/admin-list.component';
import { CustomerAddComponent } from './pages/admin/customer/customer-add/customer-add.component';
import { CustomerListComponent } from './pages/admin/customer/customer-list/customer-list.component';
import { EmployerListComponent } from './pages/admin/employer/employer-list/employer-list.component';
import { BalanceTransferComponent } from './pages/customer/balance-transfer/balance-transfer.component';
import { CustomerDashboardComponent } from './pages/customer/customer-dashboard/customer-dashboard.component';
import { CustomerProfileComponent } from './pages/customer/customer-profile/customer-profile.component';
import { CustomerUnregisteredComponent } from './pages/customer/customer-unregistered/customer-unregistered.component';
import { LoginHistoryComponent } from './pages/customer/login-history/login-history.component';
import { TransactionHistoryComponent } from './pages/customer/transaction-history/transaction-history.component';
import { CustomerDetailsComponent } from './pages/employer/customer-details/customer-details.component';
import { CustomerAddAccountComponent } from './pages/employer/customer/customer-add-account/customer-add-account.component';
import { EmployerDashboardComponent } from './pages/employer/employer-dashboard/employer-dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AdminGuard } from './services/admin.guard';
import { AuthenticatedGuard } from './services/authenticated.guard';
import { CustomerGuard } from './services/customer.guard';
import { EmployerGuard } from './services/employer.guard';

const routes: Routes = [
    { path: "login", component: LoginComponent, pathMatch: "full" },
    { path: "register", component: RegisterComponent, pathMatch: "full" },
    { path: "", component: HomeComponent, pathMatch: "full" },

    {
        path: "admin", component: AdminDashboardComponent, canActivate: [AdminGuard, AuthenticatedGuard],
        children: [
            { path: "customer/list", component: CustomerListComponent },
            { path: "employer/list", component: EmployerListComponent },
            { path: "admin/list", component: AdminListComponent },
        ]
    },

    {
        path: "dashboard", component: CustomerDashboardComponent, canActivate: [CustomerGuard, AuthenticatedGuard],
        children: [
            { path: "", component: BalanceTransferComponent },
            { path: "profile", component: CustomerProfileComponent },
            { path: "transaction/history", component: TransactionHistoryComponent },
            { path: "login/history", component: LoginHistoryComponent }
        ]
    },

    {
        path: "employer", component: EmployerDashboardComponent, canActivate: [EmployerGuard, AuthenticatedGuard],
        children: [
            { path: "customer/registered", component: CustomerListComponent },
            { path: "customer/registered/detail/:accNo", component: CustomerDetailsComponent },
            { path: "customer/add", component: CustomerAddComponent },

            { path: "customer/unregistered", component: CustomerUnregisteredComponent },
            { path: "customer/unregistered/:mobNo", component: CustomerAddAccountComponent },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }

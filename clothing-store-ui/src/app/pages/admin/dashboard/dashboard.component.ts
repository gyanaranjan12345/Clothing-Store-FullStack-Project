import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  RouterLink
} from '@angular/router';

import {
  AdminService
} from '../../../core/services/admin.service';

@Component({
  selector: 'app-admin-dashboard',

  standalone: true,

  imports: [
    CommonModule,
    RouterLink
  ],

  templateUrl:
    './dashboard.component.html'
})
export class DashboardComponent
  implements OnInit {

  totalProducts = 0;

  totalOrders = 0;

  totalCustomers = 0;

  totalRevenue = 0;

  constructor(
    private adminService: AdminService
  ) {}

  ngOnInit(): void {

    this.loadDashboard();
  }

  loadDashboard(): void {

    this.adminService
      .getDashboard()
      .subscribe({

        next: data => {

          this.totalProducts =
            data.totalProducts;

          this.totalOrders =
            data.totalOrders;

          this.totalCustomers =
            data.totalCustomers;

          this.totalRevenue =
            data.totalRevenue;
        },

        error: error => {

          console.error(
            'Dashboard error',
            error
          );
        }
      });
  }
}
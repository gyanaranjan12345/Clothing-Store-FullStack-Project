import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  AdminService
} from '../../../core/services/admin.service';

@Component({
  selector: 'app-admin-orders',

  standalone: true,

  imports: [
    CommonModule
  ],

  templateUrl:
    './orders.component.html'
})
export class OrdersComponent
  implements OnInit {

  orders: any[] = [];

  constructor(
    private adminService: AdminService
  ) {}

  ngOnInit(): void {

    this.loadOrders();
  }

  loadOrders(): void {

    this.adminService
      .getOrders()
      .subscribe({

        next: orders => {

          this.orders = orders;
        }
      });
  }

  updateStatus(
    orderId: number,
    status: string
  ): void {

    this.adminService
      .updateOrderStatus(
        orderId,
        status
      )
      .subscribe({

        next: () => {

          alert(
            'Order status updated'
          );

          this.loadOrders();
        },

        error: error => {

          alert(
            error.error?.message ||
            'Unable to update order'
          );
        }
      });
  }
}
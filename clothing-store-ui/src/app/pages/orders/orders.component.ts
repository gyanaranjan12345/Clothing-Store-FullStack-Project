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
  OrderService
} from '../../core/services/order.service';

@Component({
  selector: 'app-orders',

  standalone: true,

  imports: [
    CommonModule,
    RouterLink
  ],

  templateUrl:
    './orders.component.html'
})
export class OrdersComponent
  implements OnInit {

  orders: any[] = [];

  constructor(
    private orderService:
      OrderService
  ) {}

  ngOnInit(): void {

    this.loadOrders();
  }

  loadOrders(): void {

    this.orderService
      .getMyOrders()
      .subscribe({

        next: orders => {

          this.orders = orders;
        },

        error: error => {

          console.error(error);
        }
      });
  }
}
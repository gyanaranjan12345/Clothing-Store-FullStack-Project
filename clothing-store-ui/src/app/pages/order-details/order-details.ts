import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  ActivatedRoute
} from '@angular/router';

import {
  OrderService
} from '../../core/services/order.service';

@Component({
  selector: 'app-order-details',

  standalone: true,

  imports: [
    CommonModule
  ],

  templateUrl:
    './order-details.html',

  styleUrl: 
    './order-details.css'
  
})
export class OrderDetailsComponent
  implements OnInit {

  order: any;

  statuses = [
    'PLACED',
    'CONFIRMED',
    'SHIPPED',
    'OUT_FOR_DELIVERY',
    'DELIVERED'
  ];

  constructor(
    private route: ActivatedRoute,

    private orderService:
      OrderService
  ) {}

  ngOnInit(): void {

    const id =
      Number(
        this.route.snapshot
          .paramMap
          .get('id')
      );

    this.orderService
      .getOrder(id)
      .subscribe({

        next: order => {

          this.order = order;
        }
      });
  }

  isCompleted(
    status: string
  ): boolean {

    if (!this.order) {
      return false;
    }

    return (
      this.statuses.indexOf(status)
      <=
      this.statuses.indexOf(
        this.order.orderStatus
      )
    );
  }
}
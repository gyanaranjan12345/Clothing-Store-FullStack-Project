import {
  Component
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  FormsModule
} from '@angular/forms';

import {
  Router
} from '@angular/router';

import {
  CheckoutRequest
} from '../../models/checkout';

import {
  OrderService
} from '../../core/services/order.service';

@Component({
  selector: 'app-checkout',

  standalone: true,

  imports: [
    CommonModule,
    FormsModule
  ],

  templateUrl:
    './checkout.component.html'
})
export class CheckoutComponent {

  order: CheckoutRequest = {

    fullName: '',

    phoneNumber: '',

    addressLine1: '',

    addressLine2: '',

    city: '',

    state: '',

    pincode: '',

    paymentMethod: 'COD'
  };

  submitting = false;

  constructor(
    private orderService:
      OrderService,

    private router: Router
  ) {}

  placeOrder(): void {

    this.submitting = true;

    this.orderService
      .placeOrder(this.order)
      .subscribe({

        next: response => {

          this.submitting = false;

          alert(
            'Order placed successfully!'
          );

          this.router.navigate([
            '/orders',
            response.id
          ]);
        },

        error: error => {

          this.submitting = false;

          alert(
            error.error?.message ||
            'Unable to place order'
          );
        }
      });
  }
}
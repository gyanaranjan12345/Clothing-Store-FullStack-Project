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
  Cart,
  CartItem
} from '../../models/cart';

import {
  CartService
} from '../../core/services/cart.service';

@Component({
  selector: 'app-cart',

  standalone: true,

  imports: [
    CommonModule,
    RouterLink
  ],

  templateUrl:
    './cart.component.html'
})
export class CartComponent
  implements OnInit {

  cart?: Cart;

  loading = false;

  constructor(
    private cartService:
      CartService
  ) {}

  ngOnInit(): void {

    this.loadCart();
  }

  loadCart(): void {

    this.loading = true;

    this.cartService
      .getCart()
      .subscribe({

        next: cart => {

          this.cart = cart;

          this.loading = false;
        },

        error: error => {

          console.error(error);

          this.loading = false;
        }
      });
  }

  increase(item: CartItem): void {

    this.cartService
      .updateQuantity(
        item.id,
        item.quantity + 1
      )
      .subscribe({

        next: cart => {

          this.cart = cart;
        },

        error: error => {

          alert(
            error.error?.message ||
            'Unable to update quantity'
          );
        }
      });
  }

  decrease(item: CartItem): void {

    if (item.quantity <= 1) {

      return;
    }

    this.cartService
      .updateQuantity(
        item.id,
        item.quantity - 1
      )
      .subscribe({

        next: cart => {

          this.cart = cart;
        }
      });
  }

  remove(item: CartItem): void {

    this.cartService
      .removeItem(item.id)
      .subscribe({

        next: cart => {

          this.cart = cart;
        }
      });
  }

  clearCart(): void {

    if (!confirm(
      'Clear your entire cart?'
    )) {

      return;
    }

    this.cartService
      .clearCart()
      .subscribe({

        next: () => {

          this.loadCart();
        }
      });
  }
}
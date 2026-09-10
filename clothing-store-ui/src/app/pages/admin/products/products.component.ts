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
  Product
} from '../../../models/product';

import {
  ProductService
} from '../../../core/services/product.service';

@Component({
  selector: 'app-admin-products',

  standalone: true,

  imports: [
    CommonModule,
    RouterLink
  ],

  templateUrl:
    './products.component.html'
})
export class ProductsComponent
  implements OnInit {

  products: Product[] = [];

  constructor(
    private productService: ProductService
  ) {}

  ngOnInit(): void {

    this.loadProducts();
  }

  loadProducts(): void {

    this.productService
      .getProducts()
      .subscribe({

        next: products => {

          this.products = products;
        }
      });
  }

  deleteProduct(
    id: number
  ): void {

    if (
      !confirm(
        'Are you sure you want to delete this product?'
      )
    ) {

      return;
    }

    /*
     * This method needs to exist in
     * ProductService.
     */

    this.productService
      .deleteProduct(id)
      .subscribe({

        next: () => {

          alert(
            'Product deleted successfully'
          );

          this.loadProducts();
        },

        error: error => {

          alert(
            error.error?.message ||
            'Unable to delete product'
          );
        }
      });
  }
}
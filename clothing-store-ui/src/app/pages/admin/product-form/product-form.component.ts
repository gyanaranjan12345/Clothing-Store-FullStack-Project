import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  FormsModule
} from '@angular/forms';

import {
  ActivatedRoute,
  Router
} from '@angular/router';

import {
  Product
} from '../../../models/product';

import {
  ProductService
} from '../../../core/services/product.service';

@Component({
  selector: 'app-product-form',

  standalone: true,

  imports: [
    CommonModule,
    FormsModule
  ],

  templateUrl:
    './product-form.component.html'
})
export class ProductFormComponent
  implements OnInit {

  isEdit = false;

  productId?: number;

  product: Product = {

    id: 0,

    name: '',

    description: '',

    price: 0,

    stock: 0,

    size: '',

    color: '',

    imageUrl: '',

    categoryId: 0,

    categoryName: ''
  };

  constructor(
    private productService: ProductService,

    private route: ActivatedRoute,

    private router: Router
  ) {}

  ngOnInit(): void {

    const id =
      this.route.snapshot.paramMap
        .get('id');

    if (id) {

      this.isEdit = true;

      this.productId =
        Number(id);

      this.loadProduct(
        this.productId
      );
    }
  }

  loadProduct(id: number): void {

    this.productService
      .getProduct(id)
      .subscribe({

        next: product => {

          this.product = product;
        }
      });
  }

  saveProduct(): void {

    if (this.isEdit && this.productId) {

      this.productService
        .updateProduct(
          this.productId,
          this.product
        )
        .subscribe({

          next: () => {

            alert(
              'Product updated successfully'
            );

            this.router.navigate([
              '/admin/products'
            ]);
          }
        });

    } else {

      this.productService
        .createProduct(this.product)
        .subscribe({

          next: () => {

            alert(
              'Product created successfully'
            );

            this.router.navigate([
              '/admin/products'
            ]);
          }
        });
    }
  }
}
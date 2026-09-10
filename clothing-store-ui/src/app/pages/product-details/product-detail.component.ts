import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  ActivatedRoute,
  Router
} from '@angular/router';

import {
  ProductService
} from '../../core/services/product.service';

import {
  CartService
} from '../../core/services/cart.service';

import {
  WishlistService
} from '../../core/services/wishlist.service';

import {
  Product
} from '../../models/product';

@Component({
  selector: 'app-product-details',

  standalone: true,

  imports: [
    CommonModule
  ],

  templateUrl: './product-detail.component.html',

  styleUrl: 
    './product-detail.component.css'
  
})
export class ProductDetailsComponent
  implements OnInit {

  product?: Product;

  quantity: number = 1;

  selectedSize: string = '';

  sizes: string[] = [
    'S',
    'M',
    'L',
    'XL',
    'XXL'
  ];

  loading: boolean = false;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService,
    private cartService: CartService,
    private wishlistService: WishlistService
  ) {}


  ngOnInit(): void {
    this.loadProduct();
  }


  loadProduct(): void {

    const idParam =
      this.route.snapshot.paramMap.get('id');

    const id = Number(idParam);

    if (!idParam || isNaN(id) || id <= 0) {

      console.error('Invalid product ID');

      this.router.navigate(['/products']);

      return;
    }

    this.loading = true;

    // FIX:
    // ProductService contains getProduct(),
    // not getProductById().
    this.productService
      .getProduct(id)
      .subscribe({

        next: (product: Product) => {

          this.product = product;

          this.loading = false;

        },

        error: (error: any) => {

          console.error(
            'Error loading product:',
            error
          );

          this.loading = false;

          alert(
            error?.error?.message ||
            'Unable to load product'
          );

          this.router.navigate(['/products']);
        }

      });
  }


  increaseQuantity(): void {

    if (!this.product) {
      return;
    }

    if (
      this.quantity <
      this.product.stock
    ) {

      this.quantity++;
    }
  }


  decreaseQuantity(): void {

    if (this.quantity > 1) {

      this.quantity--;
    }
  }


  selectSize(size: string): void {

    this.selectedSize = size;
  }


  addToCart(): void {

    if (!this.product) {
      return;
    }

    if (this.product.stock <= 0) {

      alert('Product is out of stock');

      return;
    }

    if (
      this.quantity >
      this.product.stock
    ) {

      alert(
        `Only ${this.product.stock} items are available`
      );

      return;
    }

    if (!this.selectedSize) {

      alert('Please select a size');

      return;
    }

    this.cartService
      .addToCart(
        this.product.id,
        this.quantity,
        this.selectedSize
      )
      .subscribe({

        next: () => {

          alert(
            'Product added to cart 🛒'
          );
        },

        error: (error: any) => {

          console.error(
            'Add to cart error:',
            error
          );

          alert(
            error?.error?.message ||
            'Unable to add product to cart'
          );
        }

      });
  }


  addToWishlist(): void {

    if (!this.product) {
      return;
    }

    this.wishlistService
      .add(this.product.id)
      .subscribe({

        next: () => {

          alert(
            'Added to wishlist ❤️'
          );
        },

        error: (error: any) => {

          console.error(
            'Add to wishlist error:',
            error
          );

          alert(
            error?.error?.message ||
            'Unable to add product to wishlist'
          );
        }

      });
  }


  buyNow(): void {

    if (!this.product) {
      return;
    }

    if (this.product.stock <= 0) {

      alert('Product is out of stock');

      return;
    }

    if (
      this.quantity >
      this.product.stock
    ) {

      alert(
        `Only ${this.product.stock} items are available`
      );

      return;
    }

    if (!this.selectedSize) {

      alert('Please select a size');

      return;
    }

    this.cartService
      .addToCart(
        this.product.id,
        this.quantity,
        this.selectedSize
      )
      .subscribe({

        next: () => {

          this.router.navigate([
            '/checkout'
          ]);
        },

        error: (error: any) => {

          console.error(
            'Buy now error:',
            error
          );

          alert(
            error?.error?.message ||
            'Unable to proceed with purchase'
          );
        }

      });
  }


  isInStock(): boolean {

    return !!this.product &&
           this.product.stock > 0;
  }


  getTotalPrice(): number {

    if (!this.product) {
      return 0;
    }

    return (
      this.product.price *
      this.quantity
    );
  }

}
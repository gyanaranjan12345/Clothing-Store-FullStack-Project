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
  RouterLink,
  ActivatedRoute
} from '@angular/router';

import {
  Product
} from '../../models/product';

import {
  Category
} from '../../models/category';

import {
  ProductService
} from '../../core/services/product.service';

import {
  CategoryService
} from '../../core/services/category.service';

@Component({
  selector: 'app-products',

  standalone: true,

  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ],

  templateUrl:
    './products.component.html',

  styleUrls: [
    './products.component.css'
  ]
})
export class ProductsComponent
  implements OnInit {

  products: Product[] = [];

  categories: Category[] = [];

  keyword = '';

  selectedCategory?: number;

  minPrice?: number;

  maxPrice?: number;

  selectedSize = '';

  selectedColor = '';

  sort = '';

  loading = false;

  constructor(
    private productService:
      ProductService,

    private categoryService:
      CategoryService,

    private route:
      ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.loadCategories();
  }

  loadProducts(): void {

    this.loading = true;

    this.productService
      .searchProducts(
        this.keyword,
        this.selectedCategory,
        this.minPrice,
        this.maxPrice,
        this.selectedSize,
        this.selectedColor,
        this.sort
      )
      .subscribe({

        next: products => {

          this.products = products;

          this.loading = false;
        },

        error: error => {

          console.error(error);

          this.loading = false;
        }
      });
  }

  loadCategories(): void {

    this.categoryService
      .getCategories()
      .subscribe({

        next: categories => {

          this.categories =
            categories;

          const categoryName =
            this.route.snapshot.queryParamMap
              .get('category');

          const category = categories.find(
            item => item.name.toLowerCase() ===
              categoryName?.toLowerCase()
          );

          this.selectedCategory =
            category?.id;

          this.loadProducts();
        }
      });
  }

  search(): void {

    this.loadProducts();
  }

  clearFilters(): void {

    this.keyword = '';

    this.selectedCategory =
      undefined;

    this.minPrice =
      undefined;

    this.maxPrice =
      undefined;

    this.selectedSize = '';

    this.selectedColor = '';

    this.sort = '';

    this.loadProducts();
  }
}
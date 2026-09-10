import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Product } from '../../models/product';
import { ProductService } from '../../core/services/product.service';
import { getProductImage } from '../../shared/utils/product-image';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy {
  products: Product[] = [];

  heroImages = [
    'https://images.unsplash.com/photo-1445205170230-053b83016050?auto=format&fit=crop&w=1800&q=85',
    'https://images.unsplash.com/photo-1483985988355-763728e1935b?auto=format&fit=crop&w=1800&q=85',
    'https://images.unsplash.com/photo-1490481651871-ab68-de25d8d6?auto=format&fit=crop&w=1800&q=85',
    'https://images.unsplash.com/photo-1496747611176-843222e1e57c?auto=format&fit=crop&w=1800&q=85'
  ];

  activeHeroImage = 0;

  private slideshowTimer?: ReturnType<typeof setInterval>;

  constructor(private productService: ProductService) {}

  getProductImage(product: Product): string {
    return getProductImage(product);
  }

  ngOnInit(): void {
    this.loadProducts();
    this.slideshowTimer = setInterval(() => {
      this.nextHeroImage();
    }, 5000);
  }

  ngOnDestroy(): void {
    if (this.slideshowTimer) {
      clearInterval(this.slideshowTimer);
    }
  }

  nextHeroImage(): void {
    this.activeHeroImage =
      (this.activeHeroImage + 1) % this.heroImages.length;
  }

  previousHeroImage(): void {
    this.activeHeroImage =
      (this.activeHeroImage - 1 + this.heroImages.length) %
      this.heroImages.length;
  }

  selectHeroImage(index: number): void {
    this.activeHeroImage = index;
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (products) => {
        this.products = products.slice(0, 4);
      },
      error: (error) => {
        console.error('Failed to load featured products', error);
      }
    });
  }
}

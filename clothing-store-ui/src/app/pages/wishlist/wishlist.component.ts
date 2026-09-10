import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { WishlistService } from '../../core/services/wishlist.service';

@Component({
  selector: 'app-wishlist',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.css'
})
export class WishlistComponent implements OnInit {

  items: any[] = [];

  constructor(
    private wishlistService: WishlistService
  ) {}

  ngOnInit(): void {
    this.loadWishlist();
  }

  private normalizeWishlist(response: any): any[] {

    if (Array.isArray(response)) {
      return response;
    }

    if (Array.isArray(response?.items)) {
      return response.items;
    }

    if (Array.isArray(response?.products)) {
      return response.products;
    }

    if (Array.isArray(response?.content)) {
      return response.content;
    }

    return [];
  }

  loadWishlist(): void {

    this.wishlistService.getWishlist().subscribe({

      next: (response: any) => {
        this.items = this.normalizeWishlist(response);
      },

      error: (error: HttpErrorResponse) => {

        console.error(
          'Failed to load wishlist',
          error
        );

        this.items = [];
      }

    });
  }

  removeFromWishlist(item: any): void {

    const productId =
      item.productId ??
      item.id ??
      item.product?.id;

    if (!productId) {
      return;
    }

    this.wishlistService
      .remove(productId)
      .subscribe({

        next: () => {
          this.loadWishlist();
        },

        error: (error: HttpErrorResponse) => {

          alert(
            error.error?.message ||
            'Unable to remove item from wishlist'
          );

        }

      });
  }
}
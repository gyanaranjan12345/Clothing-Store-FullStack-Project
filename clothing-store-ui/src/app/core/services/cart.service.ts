import {
  Injectable
} from '@angular/core';

import {
  HttpClient
} from '@angular/common/http';

import {
  Observable
} from 'rxjs';

import {
  Cart
} from '../../models/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private apiUrl =
    'http://localhost:8080/api/cart';

  constructor(
    private http: HttpClient
  ) {}

  getCart(): Observable<Cart> {

    return this.http.get<Cart>(
      this.apiUrl
    );
  }

  addToCart(
    productId: number,
    quantity: number,
    size: string
  ): Observable<Cart> {

    return this.http.post<Cart>(
      `${this.apiUrl}/items`,
      {
        productId,
        quantity,
        size
      }
    );
  }

  updateQuantity(
    itemId: number,
    quantity: number
  ): Observable<Cart> {

    return this.http.put<Cart>(
      `${this.apiUrl}/items/${itemId}`,
      {
        quantity
      }
    );
  }

  removeItem(
    itemId: number
  ): Observable<Cart> {

    return this.http.delete<Cart>(
      `${this.apiUrl}/items/${itemId}`
    );
  }

  clearCart(): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/clear`
    );
  }
}
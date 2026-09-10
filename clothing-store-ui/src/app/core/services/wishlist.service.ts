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
  WishlistItem
} from '../../models/wishlist';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  private apiUrl =
    'http://localhost:8080/api/wishlist';

  constructor(
    private http: HttpClient
  ) {}

  getWishlist():
    Observable<WishlistItem[]> {

    return this.http.get<WishlistItem[]>(
      this.apiUrl
    );
  }

  add(
    productId: number
  ): Observable<any> {

    return this.http.post(
      `${this.apiUrl}/${productId}`,
      {}
    );
  }

  remove(
    productId: number
  ): Observable<any> {

    return this.http.delete(
      `${this.apiUrl}/${productId}`
    );
  }
}
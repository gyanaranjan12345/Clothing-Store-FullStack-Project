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
  CheckoutRequest
} from '../../models/checkout';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl =
    'http://localhost:8080/api/orders';

  constructor(
    private http: HttpClient
  ) {}

  placeOrder(
    request: CheckoutRequest
  ): Observable<any> {

    return this.http.post(
      this.apiUrl,
      request
    );
  }

  getMyOrders(): Observable<any[]> {

    return this.http.get<any[]>(
      `${this.apiUrl}/my`
    );
  }

  getOrder(
    id: number
  ): Observable<any> {

    return this.http.get(
      `${this.apiUrl}/${id}`
    );
  }
}
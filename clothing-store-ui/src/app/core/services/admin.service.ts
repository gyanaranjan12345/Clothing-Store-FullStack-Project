import { Injectable } from '@angular/core';

import {
  HttpClient
} from '@angular/common/http';

import {
  Observable
} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private apiUrl =
    'http://localhost:8080/api/admin';

  constructor(
    private http: HttpClient
  ) {}

  getDashboard(): Observable<any> {

    return this.http.get(
      `${this.apiUrl}/dashboard`
    );
  }

  getOrders(): Observable<any[]> {

    return this.http.get<any[]>(
      `${this.apiUrl}/orders`
    );
  }

  updateOrderStatus(
    orderId: number,
    status: string
  ): Observable<any> {

    return this.http.put(
      `${this.apiUrl}/orders/${orderId}/status`,
      {
        status
      }
    );
  }
}
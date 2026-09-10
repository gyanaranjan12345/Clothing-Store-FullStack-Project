import { Injectable } from '@angular/core';

import {
  HttpClient,
  HttpParams
} from '@angular/common/http';

import {
  Observable
} from 'rxjs';

import {
  Product
} from '../../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl =
    'http://localhost:8080/api/products';

  constructor(
    private http: HttpClient
  ) {}

  getProducts(): Observable<Product[]> {

    return this.http.get<Product[]>(
      this.apiUrl
    );
  }

  getProduct(
    id: number
  ): Observable<Product> {

    return this.http.get<Product>(
      `${this.apiUrl}/${id}`
    );
  }

  searchProducts(
    keyword: string,
    categoryId?: number,
    minPrice?: number,
    maxPrice?: number,
    size?: string,
    color?: string,
    sort?: string
  ): Observable<Product[]> {

    let params = new HttpParams();

    if (keyword) {
      params = params.set(
        'keyword',
        keyword
      );
    }

    if (categoryId) {
      params = params.set(
        'categoryId',
        categoryId
      );
    }

    if (minPrice !== undefined) {
      params = params.set(
        'minPrice',
        minPrice
      );
    }

    if (maxPrice !== undefined) {
      params = params.set(
        'maxPrice',
        maxPrice
      );
    }

    if (size) {
      params = params.set(
        'size',
        size
      );
    }

    if (color) {
      params = params.set(
        'color',
        color
      );
    }

    if (sort) {
      params = params.set(
        'sort',
        sort
      );
    }

    return this.http.get<Product[]>(
      `${this.apiUrl}/search`,
      { params }
    );
  }

  createProduct(
    product: Product
  ): Observable<Product> {

    return this.http.post<Product>(
      this.apiUrl,
      product
    );
  }

  updateProduct(
    id: number,
    product: Product
  ): Observable<Product> {

    return this.http.put<Product>(
      `${this.apiUrl}/${id}`,
      product
    );
  }

  deleteProduct(
    id: number
  ): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );
  }
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl =
    'http://localhost:8080/api/auth';

  constructor(
    private http: HttpClient
  ) {}

  login(email: string, password: string): Observable<any> {

    return this.http.post<any>(
      `${this.apiUrl}/login`,
      {
        email,
        password
      }
    ).pipe(
      tap(response => {

        localStorage.setItem(
          'token',
          response.token
        );

        localStorage.setItem(
          'role',
          response.role
        );
      })
    );
  }

  register(
    name: string,
    email: string,
    password: string
  ): Observable<any> {

    return this.http.post(
      `${this.apiUrl}/register`,
      {
        name,
        email,
        password
      }
    );
  }

  getToken(): string | null {

    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {

    return !!this.getToken();
  }

  getRole(): string | null {

    return localStorage.getItem('role');
  }

  logout(): void {

    localStorage.removeItem('token');

    localStorage.removeItem('role');
  }
}
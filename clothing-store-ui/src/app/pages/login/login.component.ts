import {
  Component
} from '@angular/core';

import {
  FormsModule
} from '@angular/forms';

import {
  Router,
  RouterLink
} from '@angular/router';

import {
  AuthService
} from '../../core/services/auth.service';

@Component({
  selector: 'app-login',

  standalone: true,

  imports: [
    FormsModule,
    RouterLink
  ],

  templateUrl:
    './login.component.html'
})
export class LoginComponent {

  email = '';

  password = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(): void {

    this.authService
      .login(
        this.email,
        this.password
      )
      .subscribe({

        next: () => {

          this.router.navigate(['/']);
        },

        error: error => {

          alert(
            error.error?.message ||
            'Invalid email or password'
          );
        }
      });
  }
}
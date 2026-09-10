import {
  Component
} from '@angular/core';

import {
  Router,
  RouterLink
} from '@angular/router';

import {
  NgIf
} from '@angular/common';

import {
  AuthService
} from '../../core/services/auth.service';

@Component({
  selector: 'app-navbar',

  standalone: true,

  imports: [
    RouterLink,
    NgIf
  ],

  templateUrl:
    './navbar.component.html'
})
export class NavbarComponent {

  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  logout(): void {

    this.authService.logout();

    this.router.navigate(['/login']);
  }
}
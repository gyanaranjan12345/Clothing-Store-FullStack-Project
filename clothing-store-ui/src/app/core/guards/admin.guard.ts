import { inject } from '@angular/core';

import {
  CanActivateFn,
  Router
} from '@angular/router';

import {
  AuthService
} from '../services/auth.service';

export const adminGuard: CanActivateFn = () => {

  const authService = inject(AuthService);
  const router = inject(Router);

  if (!authService.isLoggedIn()) {

    router.navigate(['/login']);

    return false;
  }

  if (authService.getRole() === 'ADMIN') {

    return true;
  }

  alert('Access denied. Admin only.');

  router.navigate(['/']);

  return false;
};
import { Routes } from '@angular/router';

import { HomeComponent }
  from './pages/home/home.component';

import { ProductsComponent }
  from './pages/products/products.component';

import { ProductDetailsComponent }
  from './pages/product-details/product-detail.component';

import { CartComponent }
  from './pages/cart/cart.component';

import { CheckoutComponent }
  from './pages/checkout/checkout.component';

import { LoginComponent }
  from './pages/login/login.component';

import { OrdersComponent }
  from './pages/orders/orders.component';

import { OrderDetailsComponent }
  from './pages/order-details/order-details';

import { DashboardComponent }
  from './pages/admin/dashboard/dashboard.component';

import { authGuard }
  from './core/guards/auth.guard';

import { adminGuard }
  from './core/guards/admin.guard';


export const routes: Routes = [

  // =========================
  // PUBLIC ROUTES
  // =========================

  {
    path: '',
    component: HomeComponent
  },

  {
    path: 'home',
    component: HomeComponent
  },

  {
    path: 'products',
    component: ProductsComponent
  },

  {
    path: 'products/:id',
    component: ProductDetailsComponent
  },

  {
    path: 'login',
    component: LoginComponent
  },


  // =========================
  // USER ROUTES
  // =========================

  {
    path: 'cart',
    component: CartComponent,
    canActivate: [authGuard]
  },

  {
    path: 'checkout',
    component: CheckoutComponent,
    canActivate: [authGuard]
  },

  {
    path: 'orders',
    component: OrdersComponent,
    canActivate: [authGuard]
  },

  {
    path: 'orders/:id',
    component: OrderDetailsComponent,
    canActivate: [authGuard]
  },


  // =========================
  // ADMIN ROUTES
  // =========================

  {
    path: 'admin',
    canActivate: [adminGuard],

    children: [

      {
        path: '',
        component: DashboardComponent
      }

    ]
  },


  // =========================
  // FALLBACK
  // =========================

  {
    path: '**',
    redirectTo: ''
  }

];
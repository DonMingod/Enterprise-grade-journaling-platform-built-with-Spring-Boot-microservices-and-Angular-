import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth-service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'];
    let userRoles: string[] = [];
    this.authService.currentUser.subscribe(user => {
      userRoles = user?.roles ?? [];
    });

    if (!expectedRoles || expectedRoles.length === 0) {
      return true;
    }

    if (userRoles.some(role => expectedRoles.includes(role))) {
      return true;
    }

    this.router.navigate(['/dashboard']);
    return false;
  }
}
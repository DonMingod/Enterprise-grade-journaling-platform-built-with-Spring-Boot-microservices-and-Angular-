import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { BehaviorSubject, pipe, tap } from 'rxjs';
import { AuthResponseDto } from '../../models/auth/auth-response-dto';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthRequestDto } from '../../models/auth/auth-request-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private apiUrl= `${environment.apiUrl}/auth-service/auth`;
  private curreUserObjectKey= new  BehaviorSubject<AuthResponseDto | null>(null);
  public currentUser = this.curreUserObjectKey.asObservable();

  constructor(private http: HttpClient,
              private router: Router) { 
    const user = localStorage.getItem('user');
    if (user) this.curreUserObjectKey.next(JSON.parse(user));
  }

  login (credentials: AuthRequestDto){
    return this.http.post<AuthResponseDto>(`${this.apiUrl}/login`, credentials)
     .pipe(tap((res) => {
        this.curreUserObjectKey.next(res);
        localStorage.setItem('user', JSON.stringify(res));
        localStorage.setItem('token', res.token);
      }));
  }

 logout() {
    this.curreUserObjectKey.next(null);
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  getToken(){return localStorage.getItem('token');}

   isAuthenticated(): boolean { return !!this.getToken(); }
  isAdmin(): boolean { return this.curreUserObjectKey.value?.roles.includes('ADMIN') ?? false; }
  isUser(): boolean { return this.curreUserObjectKey.value?.roles.includes('USER') ?? false; }

}

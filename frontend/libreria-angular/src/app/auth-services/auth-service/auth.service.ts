import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// Base URL for API requests
const BASIC_URL = ['http://localhost:8080/'];

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  // Method to handle user registration
  signup(signupRequest: any): Observable<any> {
    // Send a POST request to the server's signup endpoint with the provided data
    return this.http.post<[]>(BASIC_URL + 'api/auth/signup', signupRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + 'api/auth/login', loginRequest);
  }
}

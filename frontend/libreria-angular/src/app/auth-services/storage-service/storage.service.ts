import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() {}

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string | null {
    return localStorage.getItem(TOKEN);
  }

  // Retrieve user information from local storage and parse it as an object
  static getUser(): any {
    const userString = localStorage.getItem(USER);

    if (!userString) {
      return null;
    }

    return JSON.parse(userString);
  }

  // Get the user role from the parsed user object, or return an empty string if the user is not available
  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) {
      return '';
    }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    console.log('Role:', role); // Log the role for debugging
    return role.toLowerCase() === 'admin';
  }

  static isUSerLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role.toLowerCase() === 'user';
  }
}

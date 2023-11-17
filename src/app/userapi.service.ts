import { Injectable } from '@angular/core';
import { UserDto } from 'src/models/login.modules';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserapiService {
  private baseUrl = 'http://localhost:8080/api/v1/auth/user';

  constructor(private http: HttpClient) { }

  addUser(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(`${this.baseUrl}/addUser`, user);
  }

  getLogin(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(`${this.baseUrl}/login`, user);
  }
}

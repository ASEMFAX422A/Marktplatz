import { Injectable } from '@angular/core';
import { UserDto } from 'src/models/login.modules';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserapiService {
  private loginRequestSubject = new BehaviorSubject<boolean>(false);
  loginRequest$ = this.loginRequestSubject.asObservable();
  private baseUrl = 'http://localhost:8080/api/v1/auth/user';

  constructor(private http: HttpClient) { }

  updateLoginRequest(status: boolean) {
    this.loginRequestSubject.next(status);
  }

  addUser(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(`${this.baseUrl}/addUser`, user);
  }

  getLogin(user: UserDto): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/login`, user);
  }

}

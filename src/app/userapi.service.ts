import { Injectable } from '@angular/core';
import { UserDto } from 'src/models/login.modules';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { NumberSymbol } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class UserapiService {
  private loginRequestSubject = new BehaviorSubject<boolean>(false);
  loginRequest$ = this.loginRequestSubject.asObservable();
  private usernameSubject = new BehaviorSubject<string>('');
  private nameSubject = new BehaviorSubject<string>('');
  private emailSubject = new BehaviorSubject<string>('');
  private passwordSubject = new BehaviorSubject<string>('');
  private profilePicSubject = new BehaviorSubject<string>('');
  private roleSubject = new BehaviorSubject<string>("");
  private idSubject = new BehaviorSubject<number>(0);


  username$: Observable<string> = this.usernameSubject.asObservable();
  name$: Observable<string> = this.nameSubject.asObservable();
  email$: Observable<string> = this.emailSubject.asObservable();
  password$: Observable<string> = this.passwordSubject.asObservable();
  profilePic$: Observable<string> = this.profilePicSubject.asObservable();
  role$: Observable<string> = this.roleSubject.asObservable();
  id$: Observable<number> = this.idSubject.asObservable();


  private baseUrl = 'http://localhost:8080/api/v1/auth/user';

  constructor(private http: HttpClient) { }

  updateSharedData(username: string, name: string, email: string, password: string, profilePic: string, id: number, role: string) {
  this.usernameSubject.next(username);
  this.nameSubject.next(name);
  this.emailSubject.next(email);
  this.passwordSubject.next(password);
  this.profilePicSubject.next(profilePic);
  this.roleSubject.next(role);
  this.idSubject.next(id);
  }

  updateLoginRequest(status: boolean) {
    this.loginRequestSubject.next(status);
  }

  addUser(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(`${this.baseUrl}/addUser`, user);
  }

  getLogin(user: UserDto): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/login`, user);
  }

  getAllUsers():Observable<UserDto> {
    return this.http.get<UserDto>(`${this.baseUrl}/getAll`);
  }

  getUserByUsername(user: UserDto):Observable<UserDto> {
    return this.http.post<UserDto>(`${this.baseUrl}/getUserByUsername`, user);
  }
}

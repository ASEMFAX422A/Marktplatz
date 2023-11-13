import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductapiService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getDaten(): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/daten`);
  }

}

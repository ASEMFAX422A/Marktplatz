import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateproductComponent } from './createproduct/createproduct.component';

@Injectable({
  providedIn: 'root'
})
export class ProductapiService {
  private baseUrl = 'http://localhost:8080/anzeige'; // Stelle sicher, dass dies deine Backend-URL ist

  constructor(private http: HttpClient) { }

  getAllAnzeigen(): Observable<CreateproductComponent[]> {
    return this.http.get<CreateproductComponent[]>(`${this.baseUrl}/getAll`);
  }

  getAnzeigenByUserId(uId: number): Observable<CreateproductComponent[]> {
    return this.http.get<CreateproductComponent[]>(`${this.baseUrl}/getAll/${uId}`);
  }

  getAnzeigeById(id: number): Observable<CreateproductComponent> {
    return this.http.get<CreateproductComponent>(`${this.baseUrl}/getAnzeige/${id}`);
  }

  getAnzeigeByName(name: string): Observable<CreateproductComponent[]> {
    return this.http.get<CreateproductComponent[]>(`${this.baseUrl}/getAnzeigeByName/${name}`);
  }

  addAnzeige(anzeige: any): Observable<CreateproductComponent> {
    return this.http.post<CreateproductComponent>(`${this.baseUrl}/addAnzeige`, anzeige);
  }

  updateAnzeige(anzeige: CreateproductComponent): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateAnzeige`, anzeige);
  }

  deleteAnzeige(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteAnzeige/${id}`);
  }
}

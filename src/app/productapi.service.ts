import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnzeigeDto } from 'src/models/anzeige.models';

@Injectable({
  providedIn: 'root'
})
export class ProductapiService {
  private baseUrl = 'http://localhost:8080/api/v1/anzeige';

  constructor(private http: HttpClient) { }

  addAnzeige(anzeige: AnzeigeDto): Observable<AnzeigeDto> {
    return this.http.post<AnzeigeDto>(`${this.baseUrl}/addAnzeige`, anzeige);
  }
}

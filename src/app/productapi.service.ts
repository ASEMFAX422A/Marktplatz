import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnzeigeDto } from 'src/models/anzeige.models';

@Injectable({
  providedIn: 'root'
})
export class ProductapiService {
  private baseUrl = 'http://localhost:8080/api/v1/anzeige';
  userid : any = localStorage.getItem('user_id');


  constructor(private http: HttpClient) { }

  addAnzeige(anzeige: AnzeigeDto): Observable<AnzeigeDto> {
    console.log(this.userid);
    return this.http.post<AnzeigeDto>(`${this.baseUrl}/addAnzeige/${this.userid}`, anzeige);
  }

  getAllAnzeigen(): Observable<AnzeigeDto[]> {
    return this.http.get<AnzeigeDto[]>(`${this.baseUrl}/getAll`);
  }

  updateAnzeige(anzeige: AnzeigeDto): Observable<AnzeigeDto> {
    return this.http.put<AnzeigeDto>(`${this.baseUrl}/updateAnzeige/${anzeige.id}`, anzeige);
  }

  getAnzeigeByName(name: string): Observable<AnzeigeDto> {
    const headers = new HttpHeaders().set('Accept-Encoding', 'gzip');
    const url = `${this.baseUrl}/getAnzeigeByName/${name}`;
    return this.http.get<AnzeigeDto>(url,{headers});
  }

  getAnzeigeByID(user: AnzeigeDto): Observable<AnzeigeDto> {
    return this.http.get<AnzeigeDto>(`${this.baseUrl}/getAnzeige/{id}`);
  }
}

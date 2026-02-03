import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { DiaryNoteDtoService } from '../../models/diary-note.dto.service';

@Injectable({
  providedIn: 'root'
})
export class DiaryService {
   
  private baseUrl = `${environment.apiUrl}/diary-service/diary-entries`;
  
  constructor(private http: HttpClient) { }
 
  getAll() { return this.http.get<DiaryNoteDtoService[]>(this.baseUrl); }
 
 getById(id: number) { return this.http.get<DiaryNoteDtoService>(`${this.baseUrl}/${id}`); }
 
 create(note: DiaryNoteDtoService) { return this.http.post<DiaryNoteDtoService>(this.baseUrl, note); }


}

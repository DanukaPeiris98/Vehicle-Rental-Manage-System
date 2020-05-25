import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { motorBike } from './model/motorBike';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MotorBikeServiceService {
  filterBikes(bikeMake: String):Observable<motorBike[]> {
    return this.http.get<motorBike[]>(`http://localhost:8080/filteringBikes?make=${bikeMake}`);
   }
   getMake(): Observable<String[]>{
    return this.http.get<String[]>('http://localhost:8080/bikeMake');
  };

  constructor(private http: HttpClient) { }

  getAllMotorbikes(): Observable<motorBike[]>{
    return this.http.get<motorBike[]>('http://localhost:8080/motorBikes');
  }
}

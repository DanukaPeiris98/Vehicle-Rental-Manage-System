import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { car } from './model/car';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {
  
 
 
  filterCars(carMake: String):Observable<car[]> {
   return this.http.get<car[]>(`http://localhost:8080/filteringCars?make=${carMake}`);
  }
  getMake(): Observable<String[]>{
    return this.http.get<String[]>('http://localhost:8080/carMake');
  };
  constructor(private http: HttpClient) {}

  getAllCars(): Observable<car[]>{
    return this.http.get<car[]>('http://localhost:8080/cars');
  }
}

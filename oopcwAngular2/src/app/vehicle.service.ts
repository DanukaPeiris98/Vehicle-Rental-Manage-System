import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  bookRide(plateNumber: String, pickupDate: Date, dropoffDate: Date):Observable<String> {
    return this.http.post<String>(`http://localhost:8080/insertOrder`,{
      plateNumber: plateNumber,
      pickup: pickupDate,
      drop: dropoffDate
    });

  }

  checkAvailability(plateNumber: String, pickDate: Date, dropDate: Date): Observable<String> {
    return this.http.get<String>(`http://localhost:8080/filteringDateMake?plateNumber=${plateNumber}&pickup=${pickDate}&drop=${dropDate}`);
  }
}

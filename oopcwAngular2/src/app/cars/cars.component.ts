import { Component, OnInit } from '@angular/core';
import { CarServiceService } from '../car-service.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { car } from '../model/car';
import { ModalComponent } from '../modal/modal.component';
import { VehicleService } from '../vehicle.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})

export class CarsComponent implements OnInit {
  cars:car[];
  makes:String[];
  pickupDate: Date;
  dropoffDate: Date;
  status: String;
  plateNumber: String;
  constructor(private carService:CarServiceService,
     private modalService: NgbModal,
     private vehicleService: VehicleService) { }

  ngOnInit() {
    this.getCars();
  this.getMake();
    
  }

  
  getCars():void{
    this.carService.getAllCars().subscribe(cars=>this.cars=cars);

  }

  getMake():void{
    this.carService.getMake().subscribe(make=>this.makes=make);
  }

  filterCars(make: String): void {
    if(make=="default"){
this.getCars();
    }else{
      this.carService.filterCars(make).subscribe(cars=>this.cars=cars);
    }
    
  }

  checkAvailability(): void{
    this.vehicleService.checkAvailability(this.plateNumber, this.pickupDate, this.dropoffDate).subscribe((response)=>{
     this.status=response['status'];
     if(this.status=='unavailable'){
      const modalRef = this.modalService.open(ModalComponent);
      modalRef.componentInstance.status = 'Vehicle is unavailable';
     }else{
      const modalRef = this.modalService.open(ModalComponent);
      modalRef.componentInstance.status = 'Vehicle is available';
     }
    });
  }

  bookRide(): void{
    this.vehicleService.bookRide(this.plateNumber, this.pickupDate, this.dropoffDate).subscribe((response)=>{
      this.status=response['status'];
     if(this.status=='success'){
      const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.status = 'Ride resevered successfuly';
     }else{
      const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.status = 'Reservation was unsuccessful';
     }
    });
  }
 

}

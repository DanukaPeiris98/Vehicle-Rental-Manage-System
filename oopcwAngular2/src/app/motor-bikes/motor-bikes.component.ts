import { Component, OnInit } from '@angular/core';
import { motorBike } from '../model/motorBike';
import { MotorBikeServiceService } from '../motor-bike-service.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from '../modal/modal.component';
import { VehicleService } from '../vehicle.service';

@Component({
  selector: 'app-motor-bikes',
  templateUrl: './motor-bikes.component.html',
  styleUrls: ['./motor-bikes.component.css']
})
export class MotorBikesComponent implements OnInit {
  motorBikes:motorBike[];
  makes:String[];
  pickupDate: Date;
  dropoffDate: Date;
  status: String;
  plateNumber: String;
  constructor(private motorBikeService: MotorBikeServiceService,
    private modalService: NgbModal,
    private vehicleService: VehicleService) { }

  ngOnInit() {
    this.getMotorBikes();
   this.getMake();
      }
  getMotorBikes():void{
    this.motorBikeService.getAllMotorbikes().subscribe(motorBikes=>this.motorBikes=motorBikes);
  }
  getMake():void{
    this.motorBikeService.getMake().subscribe(make=>this.makes=make);
  }
  filterBikes(make: String): void {
    if(make=="default"){
      this.motorBikeService.getAllMotorbikes().subscribe(motorBikes=>this.motorBikes=motorBikes);
    }else{
      this.motorBikeService.filterBikes(make).subscribe(motorBikes=>this.motorBikes=motorBikes);
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



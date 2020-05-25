import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarsComponent } from './cars/cars.component';
import { HttpClientModule } from '@angular/common/http';
import { MotorBikesComponent } from './motor-bikes/motor-bikes.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './modal/modal.component'
@NgModule({
  declarations: [
    AppComponent,
    CarsComponent,
    MotorBikesComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [ModalComponent],
  exports: [CarsComponent],
})
export class AppModule { }

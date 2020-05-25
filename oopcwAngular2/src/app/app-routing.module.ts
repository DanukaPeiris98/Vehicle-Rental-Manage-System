import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarsComponent } from './cars/cars.component';
import { MotorBikesComponent } from './motor-bikes/motor-bikes.component';
import { AppComponent } from './app.component';


const routes: Routes = [
  {path: 'cars',component: CarsComponent},
  {path: 'motorBikes',component: MotorBikesComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

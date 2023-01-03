import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbDatepickerModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, shareReplay } from 'rxjs';
import { AuthService, USER_ID } from 'src/app/service/auth.service';
import { AsYouType } from 'libphonenumber-js';
import { Country, State, City, ICountry, IState }  from 'country-state-city';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ProfileService } from 'src/app/service/student/profile.service';
import { Profile } from 'src/app/models/profile';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  connectedUser$: Observable<any>;
  age: number;
  closeResult: string;
  genders = ["Female", "Male", "Other"]
  countries:ICountry[];
  selectedCountry:ICountry;
  states:IState[];
  private country = "CA";
  private isStepTwo :boolean;
  private connectedUser:Profile;
  userProfileForm:FormGroup;

  constructor(private authService: AuthService, private profileService: ProfileService, private modalService: NgbModal) { }

  ngOnInit() {
    this.connectedUser$ = this.authService.getConnectedUser();
    this.showAge()
    this.getStatesOfCountry()
    this.initialize()
  }

  initialize(){
    this.userProfileForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      telephoneNumber: new FormControl('', [Validators.required]),
      gender: new FormControl(null, Validators.required),
      dateOfBirth: new FormControl('', Validators.required),
      aboutMe: new FormControl(),
      street: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      province: new FormControl(null, Validators.required),
      country: new FormControl('Canada', Validators.required),
      postalCode: new FormControl('', Validators.required),
      apartmentNumber: new FormControl() })
      this.loadConnectedUser()
  }

  showAge() {
    this.connectedUser$.subscribe((data) => {
      const convertAge = new Date(data.dateOfBirth);
      const timeDiff = Math.abs(Date.now() - convertAge.getTime());
      this.age = Math.floor((timeDiff / (1000 * 3600 * 24)) / 365);
    })

  }

  open(content) {
    this.getAllCountries()
    // this.loadConnectedUserInForm()
    this.modalService.open(content, { size: 'lg', centered: true }).result.then((result) => {
      this.closeResult = 'Closed with: $result';
    }, (reason) => {
      this.closeResult = 'Dismissed $this.getDismissReason(reason)';
    });
  }

  
  phoneNumberFormatter(event) {
    const inputField = document.getElementById('input-phone-number') as HTMLInputElement
    inputField.value = new AsYouType('CA').input(event.target.value)
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return 'with: $reason';
    }
  }

  getAllCountries(){
    this.countries = Country.getAllCountries();
  }

  getStatesOfCountry(){
      this.states = State.getStatesOfCountry(this.country);
  }

  save(){
    console.log(this.userProfileForm.value);
    this.profileService.updateProfile(localStorage.getItem(USER_ID), this.userProfileForm.value);
    this.modalService.dismissAll('Close click')
    this.isStepTwo = false;
  }

  loadConnectedUser(){
    this.connectedUser$.subscribe((data)=>{ 
      this.connectedUser = data;  
      console.log(this.connectedUser);
         
      this.userProfileForm.controls.firstName.setValue(data.firstName)
      this.userProfileForm.controls.lastName.setValue(data.lastName)
      this.userProfileForm.controls.email.setValue(data.email)
      this.userProfileForm.controls.telephoneNumber.setValue(new AsYouType('CA').input(data.telephoneNumber))
      this.userProfileForm.controls.gender.setValue(data.gender)
      this.userProfileForm.controls.dateOfBirth.setValue(data.dateOfBirth)
      this.userProfileForm.controls.street.setValue(data.address.street)
      this.userProfileForm.controls.apartmentNumber.setValue(data.address.apartmentNumber)
      this.userProfileForm.controls.city.setValue(data.address.city)
      this.userProfileForm.controls.province.setValue(data.address.province)
      this.userProfileForm.controls.postalCode.setValue(data.address.postalCode)
    })
  }
}

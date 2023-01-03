import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { BehaviorSubject, Observable, shareReplay } from 'rxjs';
import { STUDENT_BASE_URL } from 'src/environments/environment';

export const USER_ID = "userId";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  isLoggedIn:Promise<boolean>;

  constructor(private http: HttpClient, private keycloakService : KeycloakService) {
    keycloakService.loadUserProfile().then(user => localStorage.setItem(USER_ID, user.id))
   }

   getConnectedUser():Observable<any>{
    this.isLoggedIn = this.keycloakService.isLoggedIn();
    if(this.isLoggedIn){
      const userId = localStorage.getItem(USER_ID)
      return this.http.get(`${STUDENT_BASE_URL}`+"/profile/"+`${userId}`).pipe(shareReplay());
    }
   }

   isConnected():Promise<boolean>{
    return this.keycloakService.isLoggedIn()
   }
   
   logout(){
    this.keycloakService.logout()
   }

   autoLogout(){   
    if(this.keycloakService.isTokenExpired()){
      this.logout()
    }
   }
}

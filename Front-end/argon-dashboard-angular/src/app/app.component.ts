import { Component, DoCheck, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'argon-dashboard-angular';

  constructor(private authService:AuthService, private keycloakService : KeycloakService){
    
  }
  ngOnInit(): void {
    setInterval(()=>{
      this.authService.autoLogout()
    }, 300000)
  } 

}

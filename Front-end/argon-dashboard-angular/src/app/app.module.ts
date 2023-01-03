import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080/',
        realm: 'tech-academy',
        clientId: 'tech-academy'
      },
      initOptions: {
       onLoad:'login-required'
      }
    });
  }

@NgModule({
  imports: [
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
    KeycloakAngularModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent
  ],
  providers: [
    {
    provide: APP_INITIALIZER,
    useFactory: initializeKeycloak,
    multi: true,
    deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

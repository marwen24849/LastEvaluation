import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakServiceUserService } from './keycloak-service-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'Evaluation-Front';
  constructor( public kc : KeycloakService, public kcservice: KeycloakServiceUserService) {}

  ngOnInit(): void {
    
    if(this.kc.isLoggedIn()){
      this.kc.loadUserProfile().then(profile =>{
        const username = profile.username || 'default_username';
        sessionStorage.setItem('username', username);
        this.kcservice.profile = profile;
    }
        
        );
    }
  }

}

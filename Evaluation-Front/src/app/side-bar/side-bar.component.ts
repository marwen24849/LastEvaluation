import { Component, OnInit, Renderer2 } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { KeycloakServiceUserService } from '../keycloak-service-user.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent implements OnInit{
  constructor(private renderer: Renderer2, public kc : KeycloakService) {}
  role:string='';
  lien ='data:image/png;base64,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';

  ngOnInit(): void {
    
    const sidebar = document.querySelector("#sidebar");
    if (sidebar) {
      sidebar.classList.add('collapsed');
    }
    const toggler = document.querySelector(".btn");
    if(toggler)
      toggler.addEventListener("click", () => {
      const sidebar = document.querySelector("#sidebar");
      if (sidebar) {
        sidebar.classList.toggle('collapsed');
      }
    });


    if(this.kc.isLoggedIn()){
      this.kc.loadUserProfile().then(profile =>{
        this.profile=profile;
       });
       if(this.kc.getUserRoles().includes('mentor'))
        this.role = 'mentor';
      else if(this.kc.getUserRoles().includes('admin'))
        this.role = 'admin'
      else
        this.role='user'
         console.log(this.role)

    }
  }


  public profile? : KeycloakProfile;

  test():boolean{
    return this.role == 'admin' || this.role == 'mentor'
  }

  onlogOut(){
    this.kc.logout(window.location.origin);
  }

  async login(){
    await this.kc.login({
      redirectUri: window.location.origin
    });
  }

}

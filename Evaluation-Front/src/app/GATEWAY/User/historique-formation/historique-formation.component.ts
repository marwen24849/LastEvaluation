import { Component } from '@angular/core';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-historique-formation',
  templateUrl: './historique-formation.component.html',
  styleUrl: './historique-formation.component.css'
})
export class HistoriqueFormationComponent {

  constructor(private userService: UserServiceService){}
  formation:any;
  username:any;
  ngOnInit(): void {
    
    this.username = sessionStorage.getItem('username')
    this.userService.listFormation(this.username).subscribe(
      res=>{
        this.formation=res;
        console.log(res);
      },
      err=>{
        console.log(err);
      }
    );
  }

  inscriptionFormation(idFormation: number){
    const body = new URLSearchParams();
    body.set('id_formation', idFormation.toString());
    body.set('id_user', this.username);
    console.log('id formation : '+idFormation)
    this.userService.inscritformation(body).subscribe(
      res=>{
        window.location.reload();
      },
      err=>{
        console.log(err);
      }
    );
  }

}

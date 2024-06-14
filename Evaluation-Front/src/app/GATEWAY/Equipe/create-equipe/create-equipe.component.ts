import { Component } from '@angular/core';

import { EquipeServiceService } from '../equipe-service.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-equipe',
  templateUrl: './create-equipe.component.html',
  styleUrl: './create-equipe.component.css'
})
export class CreateEquipeComponent {

  equipeModel: any = {
    name: '',
    department: '',
    users: []
  };

  constructor(private equipeService : EquipeServiceService, private router: Router) { }

  createEquipe() {
    if (this.equipeModel.name == ''
       ||
       this.equipeModel.department == ''
       
       ) {
      return;
    }
    this.equipeService.createEquipe(this.equipeModel)
      .subscribe(
        response => {
          console.log('Equipe créée avec succès : ', response);
          this.equipeModel = {
            name: '',
            department: '',
            users: []
          };
          this.router.navigate(['/Equips']);
        },
        error => {
          console.log('Erreur lors de la création de l\'équipe : ', error);
        }
      );
  }

}

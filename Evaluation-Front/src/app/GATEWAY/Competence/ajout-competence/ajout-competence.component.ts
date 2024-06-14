import { Component } from '@angular/core';
import {SkillService} from '../skill.service'
@Component({
  selector: 'app-ajout-competence',
  templateUrl: './ajout-competence.component.html',
  styleUrl: './ajout-competence.component.css'
})
export class AjoutCompetenceComponent {

  skill = { name: '', description: '' };

  constructor(private skillService: SkillService) { }

  save() {
    this.skillService.createSkill(this.skill)
      .subscribe(
        response => {
          
          this.skill = { name: '', description: '' };
          window.location.reload();
        },
        error => {
          console.log(error);
        });
  }
}

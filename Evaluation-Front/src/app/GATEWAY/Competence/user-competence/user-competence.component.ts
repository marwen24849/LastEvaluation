import { Component, OnInit } from '@angular/core';
import { CompetenceUserService } from '../competence-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-competence',
  templateUrl: './user-competence.component.html',
  styleUrl: './user-competence.component.css'
})
export class UserCompetenceComponent implements OnInit {

  skills: any[] = [];
  filteredSkills: any[] = [];
  searchSkillName: string = '';
  niveau:number=0;
  competenceUser:any={
    userId:'',
    idCompetence:'',
    valide: false,
    level:0
  };

  constructor(private competenceUserService: CompetenceUserService,private route : Router) { }

  ngOnInit(): void {
    this.getAvailableCompetences();
  }

  getAvailableCompetences() {
    const username:any = sessionStorage.getItem('username');
    this.competenceUserService.getAvailableCompetences(username)
      .subscribe(
        data => {
          this.skills = data;
          this.filteredSkills = [...this.skills];
        },
        error => {
          console.log(error);
        }
      );
  }

  filterSkills() {
    this.filteredSkills = this.skills.filter(skill =>
      skill.name.toLowerCase().includes(this.searchSkillName.toLowerCase())
    );
  }

  

  addSkillToUser(skill: any) {
    const username:any = sessionStorage.getItem('username');
    this.competenceUser.userId=username;
    this.competenceUser.idCompetence=skill.id;

   
  }

  submitLevel(){
    if(this.niveau>20)
      this.niveau=20;
    if(this.niveau<0)
      this.niveau=0;
    this.competenceUser.level=this.niveau;
    console.log(this.competenceUser)
    this.competenceUserService.createCompetenceUser(this.competenceUser)
    .subscribe(
      response => {
        this.getAvailableCompetences();
        this.route.navigate(['/ProfileUser'])
      },
      error => {
        console.log(error);
      }
    );

  }

}
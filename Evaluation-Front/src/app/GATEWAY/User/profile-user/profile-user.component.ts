import { Component, OnInit, ViewChild } from '@angular/core';
import { UserServiceService } from '../user-service.service';

import { Chart } from 'chart.js/auto';
import { CompetenceUserService } from '../../Competence/competence-user.service';



@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrl: './profile-user.component.css'
})
export class ProfileUserComponent implements OnInit{
[x: string]: any;


  constructor(private userService : UserServiceService, private competenceUserService: CompetenceUserService){}

  UsersProfile:any={
    username:'',
    level:'',
    yearsexperience:0,
    formations:[
      {
        title:'',
        certification:''
      }
    ]

  };
  skill:any;
  competence : any=[];
  badges :any = [];

  username:any;
  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')
    this.userService.findByUsername(this.username).subscribe(
      res=>{
        this.UsersProfile=res;
      },
      err=>{
        console.log(err);
      }
    );

    this.userService.badgeColab(this.username).subscribe(
      res=>{
        this.badges=res;
      },
      err=>{
        console.log(err);
      }
    );
    this.competenceUser(this.username);
  }


  competenceUser(username:any){
    this.competenceUserService.getAvailableCompetencesForUser(username).subscribe(
      res=>{
        this.competence=res;
        this.createChart();
       
      },
      err=>{
        console.log(err);
      }
    );
  }

  callyersex(dateCreatio:any){
    const today = new Date();
    const dateCreation = new Date(dateCreatio);
    const diffYears = today.getFullYear() - dateCreation.getFullYear();
    return diffYears;
  }

  createChart(): void {
    const labels = this.competence.map((comp:any) => comp.skill.name);
    const data = this.competence.map((comp:any) => comp.level);
    const ctx = document.getElementById('myChart') as HTMLCanvasElement;
    const myChart = new Chart(ctx, {
      type: 'radar',
      data: {
        labels: labels,
        datasets: [{
          label: '# of Votes',
          data: data,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          r: { 
            beginAtZero: true
          }
        }
      }
    });
  }
  getLevelLabel(niveau: number): string {
    if (niveau <= 5) {
      return 'Débutant';
    } else if (niveau <= 10) {
      return 'Intermédiaire';
    } else if (niveau <= 15) {
      return 'Avancé';
    } else {
      return 'Expert';
    }
  }

 

  getBadgeClass(badgeType: string): string {
    switch(badgeType) {
      case 'SILVER':
        return 'offer offer-silver';
      case 'BRONZE':
        return 'offer offer-bronze';
      case 'GOLD':
        return 'offer offer-gold';
      default:
        return 'offer offer-default';
    }
  }
 
  onSelectskill(skill: any) {
    this.skill=skill;
    var btn = document.getElementById('trace-modal');
    if(btn != null)
      btn.click();

  }

}

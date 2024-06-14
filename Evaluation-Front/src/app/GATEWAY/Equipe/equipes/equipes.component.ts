import { Component, OnInit } from '@angular/core';
import { EquipeServiceService } from '../equipe-service.service';
import { UserServiceService } from '../../User/user-service.service';

@Component({
  selector: 'app-equipes',
  templateUrl: './equipes.component.html',
  styleUrl: './equipes.component.css'
})
export class EquipesComponent implements OnInit {
  equipes: any=[];
  equipesgard: any=[];
  users: any=[];
  namequipe: any='';
  quizzes: any=[];
  questions: any=[];
  equipeId:any;
  username:String='';
  usersGard:any=[];
  startIndex: number = 0;
  endIndex: number = 10;
  totalPages: number = 0;
  currentPage: number = 0;
  constructor(private equipeService: EquipeServiceService, private userService : UserServiceService) { }

  ngOnInit(): void {
    this.getEquipes();
  }



  getEquipes(): void {
    this.equipeService.getEquipes()
      .subscribe(
        (data: any) => {
          this.equipes = data;
          this.equipesgard=this.equipes;
          this.calculateTotalPages();
          this.calculateCurrentPage();
        },
        error => {
          console.log(error);
        }
      );
  }

  showUsers(users: any[],id:any): void {
    this.users = users;
    this.equipeId=id;
  }

  listQuiz(id:any){
    this.equipeId = id;
    this.equipeService.getQuizEquipeById(id).subscribe(
      res=>{
        this.quizzes=res;
      },
      err=>{
        console.log(err);
      }
    );
  }

  Questions(questions : any){
   
    this.questions= questions;
  }

  addQuizToUsersEquipe(idQuiz:any, idEquipe:any){
    this.equipeService.addQuizToUsersEquipe(idEquipe, idQuiz).subscribe(
      res=>{
        
        window.location.reload();
      },
      err=>{
        console.log(err);
      }
    );
  }

  getUsersnoInscritEquipeId(idequipe:any){
    this.equipeId=idequipe;
    this.equipeService.getUsersnoInscritEquipeId(idequipe).subscribe(
      res=>{
        this.users=res;
        this.usersGard=res;
      },
      err=>{
        console.log(err);
      }
    );
  }

  addUserToEquipe(iduser:any){
    this.equipeService.addUserToEquipe(this.equipeId, iduser).subscribe(
      (res:any)=>{
        alert("L'utilisateur "+'marwen'+" a été ajouté à l'équipe.");
        window.location.reload();
      },
      err=>{
        console.log(err);
      }
    );
  }

  supprimeruser(idu:any){
    let conf = confirm("Êtes-vous sûr(e) ?");
    if (conf === false) return;
    this.equipeService.removeUserFromEquipe(this.equipeId, idu).subscribe(
      res=>{
        window.location.reload();
      },
      err=>{
        console.log(err);
        
      }
    );
  }


  getItems(): any[] {
    return this.equipes.slice(this.startIndex, this.endIndex);
  }

 
  nextPage() {
    this.startIndex += 10;
    this.endIndex += 10;
    this.calculateCurrentPage();
    
  }

  prevPage() {
    this.startIndex -= 10;
    this.endIndex -= 10;
    this.calculateCurrentPage();
  }

  calculateTotalPages() {
    this.totalPages = Math.ceil(this.equipes.length / 10);
  }

  calculateCurrentPage() {
    const elementsPerPage = 10; 
    this.currentPage = Math.min(Math.ceil(this.endIndex / elementsPerPage), Math.ceil(this.equipes.length / elementsPerPage));
  }

  filteredEquips() {
    this.equipes=this.equipesgard;
    this.equipes = this.equipes.filter((equipe: { name: string; }) =>
      equipe.name.toLowerCase().includes(this.namequipe.toLowerCase())
    );
    this.getItems();
   
  }

  filteredUsers() {
    this.users=this.usersGard;
    this.users = this.users.filter((user: { username: string; }) =>
      user.username.toLowerCase().includes(this.username.toLowerCase())
    );
    
  }


}

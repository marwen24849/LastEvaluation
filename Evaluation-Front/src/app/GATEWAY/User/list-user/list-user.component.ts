import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { QuizServiceService } from '../../QCM-SERVICE/Qcm/quiz-service.service';
import { CompetenceUserService } from '../../Competence/competence-user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrl: './list-user.component.css'
})
export class ListUserComponent implements OnInit{

  constructor(private userService : UserServiceService, private QuizService : QuizServiceService, private competenceUserService: CompetenceUserService){}
  users:any=[];
  Quizs:any=[];
  questions:any = [] ;
  id_user: any;
  categories:any;
  selectedCategory:string='';
  searchUsername: string = '';
  usersGardrole:any=[];
  selectedRole: string = '';
  usersGard :any=[];

  ngOnInit(): void {
    this.userService.AllUser().subscribe(
      res=>{
        this.users=res;
        this.usersGard=this.users;
        this.usersGardrole=this.users;
        console.log(res);
        this.calculateTotalPages();
        this.calculateCurrentPage();
      },
      err=>{
        console.log(err);
      }
    );
    this.categories = [];
    this.QuizService.getAll().subscribe(
      (response: any) => {
        response.forEach((quiz: any) => {
          if (!this.categories.includes(quiz.category)) {
            this.categories.push(quiz.category);
          }
        });
      },
      (error: any) => {
         console.error('Une erreur s\'est produite lors de la récupération des questions :', error);
      }
    );
   
  }

  listQuiz(id_user: any){
    this.id_user=id_user;
    this.userService.quizUser(id_user).subscribe(
      res=>{
        this.Quizs=res;
        console.log(res)
      },
      err=>{
        console.log(err);
      }
    );
  }
  aff(questions : any, id: any){
    this.questions=questions;
  }

  getQuizByCategory(){
    this.QuizService.getByCategories(this.selectedCategory).subscribe(
      res=>{
        this.Quizs = res;
      },err=>{
        console.log(err);
      }
    );
  }


  AffecterQuizUser(id_quiz: any){
    this.userService.AffecterQuiz(id_quiz, this.id_user).subscribe(
      res=>{
        console.log(res);
        window.location.reload();
      },
      err=>{
        console.log(err);
      }
    );
  }





  startIndex: number = 0;
  endIndex: number = 10;
  totalPages: number = 0;
  currentPage: number = 0;

  
  getItems(): any[] {
    return this.users.slice(this.startIndex, this.endIndex);
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
    this.totalPages = Math.ceil(this.users.length / 10);
  }

  calculateCurrentPage() {
    const elementsPerPage = 10; 
    this.currentPage = Math.min(Math.ceil(this.endIndex / elementsPerPage), Math.ceil(this.users.length / elementsPerPage));
  }


   filteredUsers() {
    this.users=this.usersGard;
    this.users = this.users.filter((user: { username: string; }) =>
      user.username.toLowerCase().includes(this.searchUsername.toLowerCase())
    );
    console.log(this.users)
  }
  

  filteredUsersByRole() {
    if(this.selectedRole == 'tous'){
      this.ngOnInit();
      return;
    }
    this.users=this.usersGardrole;
    this.users = this.users.filter((user: {
      roles: any; user: any; 
    }) =>
      user.roles.includes(this.selectedRole.toLowerCase())
    );
    this.usersGard=this.users;
  }
  
  skill:any;
  skillUser(username:any){
    this.competenceUserService.getAvailableCompetencesForUser(username).subscribe(
      res=>{
        this.skill=res;
        console.log(res)
      },
      err=>{
        console.log(err);
      }
    );
  }

  validerCompetence(id:any){
    const body = {
      valide:true
    }
    this.competenceUserService.updateCompetenceUser(id,body).subscribe(
      res=>{
        this.onSelectskill()
      },
      err=>{
        console.log(err);
      }
    );
  }

  onSelectskill() {
    
    var btn = document.getElementById('trace-modal');
    if(btn != null)
      btn.click();

  }

}

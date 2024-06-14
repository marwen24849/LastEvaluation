import { Component, OnInit, ViewChild } from '@angular/core';
import { QuizServiceService } from '../quiz-service.service';
import { QuestionServiceService } from '../../Question/question-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-qcm',
  templateUrl: './list-qcm.component.html',
  styleUrl: './list-qcm.component.css'
})
export class ListQcmComponent implements OnInit{

  questions:any = [] ;
  quiz: any ;
  id: any;
  categories:any;
  selectedCategory:any;
 
  constructor(private quizService: QuizServiceService,private questionService : QuestionServiceService, private router: Router) { }

  ngOnInit(): void {
    this.quizService.getAll().subscribe(
      res =>{
        this.quiz = res;
      },
      err => {
        console.log(err)
      }
    );

    this.categories = [];
    this.quizService.getAll().subscribe(
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
  supprimer(id: any, Questions:any){
    let conf = confirm("Êtes-vous sûr(e) ?");
    if (conf === false) return;
    this.quizService.delete(id, Questions).subscribe(
      res =>{
        
      },
      err =>{
       console.log(err)
       window.location.reload();
      }
    );
  }

  aff(questions : any, id: any){
    this.questions=questions;
    this.id = id;
  }

  sup(id_Question : any){
    this.quizService.deleteQues(this.id,id_Question).subscribe(
      res=>{
        window.location.reload();
      },
      err=>{
        console.log(err)
        window.location.reload();
      }
    );
  }

  getQuizByCategory(){
    this.quizService.getByCategories(this.selectedCategory).subscribe(
      res=>{
        this.quiz = res;
      },err=>{
        console.log(err);
      }
    );
  }


}

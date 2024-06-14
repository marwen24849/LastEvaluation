import { Component } from '@angular/core';
import { QuizServiceService } from '../quiz-service.service';
import { Router } from '@angular/router';
import { QuestionServiceService } from '../../Question/question-service.service';
import { ToastrService } from 'ngx-toastr'

@Component({
  selector: 'app-ajout-qcm',
  templateUrl: './ajout-qcm.component.html',
  styleUrl: './ajout-qcm.component.css'
})
export class AjoutQcmComponent {

  
  quiz: any = {
    title: '',
    category: '',
    nbQuestion: 0,
    minimumSuccessPercentage: 0,
    difficultylevel:'',
    badgeType:'',
    name :'',
    description :'',
    type:''
  };


  
  categories : any;
  levels : any=[];
  nb : any;
  showBadgeType :any;


  constructor(private quizService: QuizServiceService,private questionService : QuestionServiceService, private router: Router) { }

  ngOnInit(): void {
    
    this.categories = [];
    this.levels = [];
    this.questionService.getAll().subscribe(
      (response: any) => {
        response.forEach((question: any) => {
          if (!this.categories.includes(question.category)) {
            this.categories.push(question.category);
          }
          if (!this.levels.includes(question.difficultylevel)) {
            this.levels.push(question.difficultylevel);
          }
        });

        
      },
      (error: any) => {
        console.error('Une erreur s\'est produite lors de la récupération des questions :', error);
      }
    );
  }

  onSubmit() : void{
    this.quizService.ajout(this.quiz).subscribe(
      response => {
       
        window.location.reload();
      },
      error => {
        console.error('Failed to add Quiz:', error);
      }
    );
  }
  nbQues(){

    this.questionService.nbQuestion(this.quiz.category, this.quiz.difficultylevel).subscribe(
      res=>{
        this.quiz.nbQuestion= res;
        this.nb=res;
      },
      err=>{
        console.log(err);
      }
    );

   
  }

}

import { Component, OnInit } from '@angular/core';
import { QuizServiceService } from '../quiz-service.service';
import { QuestionServiceService } from '../../Question/question-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-ajout-question-qcm',
  templateUrl: './ajout-question-qcm.component.html',
  styleUrl: './ajout-question-qcm.component.css'
})
export class AjoutQuestionQcmComponent implements OnInit{


  constructor(private serviceQuiz : QuizServiceService, private serviceQuestion : QuestionServiceService, private rout : ActivatedRoute, private router : Router){}

  listQuestion : any = [];
  id_Quiz : any;
  quiz : any = [];
  questions : any= [];
  levels : any=[];
  selectedlevel:any;
 
  ngOnInit(): void {
    this.rout.params.subscribe(p =>{this.id_Quiz = p['id']});
    
        this.serviceQuiz.getQuestionQuiz(this.id_Quiz).subscribe(
          res=>{
            this.listQuestion = res ;
          },
          err=> {
            console.log(err)
          }
        );
      
  }
  
  Ajout(id:any){
    this.serviceQuiz.updateQuizQus(this.id_Quiz, id).subscribe(
      res=>{
        this.router.navigate(['/listQuiz']);
      },
      err=>{
        window.location.reload();
      }
    );
  }

  

}

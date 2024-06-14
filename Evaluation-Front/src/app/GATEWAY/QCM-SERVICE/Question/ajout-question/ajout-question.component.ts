import { Component } from '@angular/core';
import { QuestionServiceService } from '../question-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ajout-question',
  templateUrl: './ajout-question.component.html',
  styleUrl: './ajout-question.component.css'
})
export class AjoutQuestionComponent {
  
  constructor(private service : QuestionServiceService,private router : Router) {
   
  }

  question: any = {
    questionTitle: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    rightAnswer: '',
    difficultylevel: '',
    category: '',
    score: 0
  };

  onSubmit() {
    if (this.question.questionTitle == ''
       ||
       this.question.option1== ''
       ||
       this.question.option1== ''
       ||
       this.question.option2== ''
       ||
       this.question.option3== ''
       ||
       this.question.option4== ''
       ||
       this.question.difficultylevel== ''
       
       ) {
      return;
    }
    this.service.add(this.question).subscribe(
      (response) => {
        console.log('Question added successfully:', response);
        this.question = {
          questionTitle: '',
          option1: '',
          option2: '',
          option3: '',
          option4: '',
          rightAnswer: '',
          difficultylevel: '',
          category: '',
          score: 0
        };
        this.router.navigate(['listQuestion'])
      },
      (error) => {
        console.error('Failed to add question:', error);
      }
    );
  }



}

import { Component, OnInit } from '@angular/core';
import { QuizServiceService } from '../quiz-service.service';
import { QuestionServiceService } from '../../Question/question-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-qcm',
  templateUrl: './update-qcm.component.html',
  styleUrl: './update-qcm.component.css'
})
export class UpdateQcmComponent implements OnInit{

  constructor(private quizService: QuizServiceService,private questionService : QuestionServiceService, private router: Router, private route : ActivatedRoute) { }
  quiz: any = {
    title: '',
    category: '',
    nbQuestion: 0,
    minimumSuccessPercentage:0
  };
  id: any;
  ngOnInit(): void {
    this.route.params.subscribe(par => {this.id = par['id']});
    this.quizService.getById(this.id).subscribe(
      res =>{
        this.quiz=res;
      },
      err =>{
        console.log(err);
      }
    );
  }

  onSubmit() : void{
    this.quizService.update(this.id, this.quiz).subscribe(
      response => {
        console.log('Quiz update successfully:', response);
        this.router.navigate(['listQuiz']);
      },
      error => {
        console.error('Failed to add Quiz:', error);
      }
    );
  }

}

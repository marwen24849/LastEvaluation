import { Component, OnInit } from '@angular/core';
import { QuestionServiceService } from '../question-service.service';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrl: './update-question.component.css'
})
export class UpdateQuestionComponent implements OnInit{

  constructor(private service : QuestionServiceService,private router : Router,private rout : ActivatedRoute) {}
  parentUrl: any;
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
  id : any;
  private previousUrl: any;
  ngOnInit(): void {
   this.rout.params.subscribe(res => {this.id = res['id']});

    /* this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      const parentRoute = this.rout.parent ? this.rout.parent.routeConfig : null;
      if (parentRoute) {
        this.previousUrl = parentRoute.path || '';
      }
    }); */

    
    this.service.getById(this.id).subscribe(
      res =>{
        this.question = res;
      },
      err=>{
        console.log(err);
      }
    );
  }
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
    this.service.update(this.id,this.question).subscribe(
      (response) => {
        console.log('Question Modifer successfully:', response);
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
        this.router.navigate(['/listQuestion']);
      },
      (error) => {
        console.error('Failed to add question:', error);
      }
    );
  }
}
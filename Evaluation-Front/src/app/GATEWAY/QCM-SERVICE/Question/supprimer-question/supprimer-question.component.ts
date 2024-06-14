import { Component, OnInit } from '@angular/core';
import { QuestionServiceService } from '../question-service.service';

@Component({
  selector: 'app-supprimer-question',
  templateUrl: './supprimer-question.component.html',
  styleUrl: './supprimer-question.component.css'
})
export class SupprimerQuestionComponent{

  constructor(private questionService: QuestionServiceService) { }

 
}

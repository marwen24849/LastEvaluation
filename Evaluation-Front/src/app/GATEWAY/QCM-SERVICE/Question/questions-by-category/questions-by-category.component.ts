import { Component } from '@angular/core';
import { QuestionServiceService } from '../question-service.service';

@Component({
  selector: 'app-questions-by-category',
  templateUrl: './questions-by-category.component.html',
  styleUrl: './questions-by-category.component.css'
})
export class QuestionsByCategoryComponent {

  constructor(private questionService: QuestionServiceService) { }

  ngOnInit(): void {
    this.categories = [];
    this.questionService.getAll().subscribe(
      (response: any) => {
        response.forEach((question: any) => {
          if (!this.categories.includes(question.category)) {
            this.categories.push(question.category);
          }
        });
      },
      (error: any) => {
        console.error('Une erreur s\'est produite lors de la récupération des questions :', error);
      }
    );
  }
  

  selectedCategory : any;
  categories: any;
  questions : any;

  getQuestionsByCategory(){
    this.questionService.getByCategory(this.selectedCategory).subscribe(
      res=>{
        this.questions = res;
      },err=>{
        console.log(err);
      }
    );
  }
  supprimer(id : any){
    this.questionService.delete(id).subscribe(
      res=>{
        window.location.reload();
      },
      err=>{
        window.location.reload();
      }
    );
  }


}

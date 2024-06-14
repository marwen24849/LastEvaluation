import { Component } from '@angular/core';
import { QuestionServiceService } from '../question-service.service';

@Component({
  selector: 'app-list-question',
  templateUrl: './list-question.component.html',
  styleUrl: './list-question.component.css'
})
export class ListQuestionComponent {
  questions: any = [];
  categories:any=[]
  selectedCategory : string='';
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
    this.questionService.getAll().subscribe(
      (response: any) => {
        this.questions = response;
        this.calculateTotalPages();
        this.calculateCurrentPage();
        
      },
      (error: any) => {
        console.error('Une erreur s\'est produite lors de la récupération des questions:', error);
      }
    );
  }
  getQuestionsByCategory(){
    this.questionService.getByCategory(this.selectedCategory).subscribe(
      res=>{
        this.questions = res;
        this.calculateTotalPages();
        this.calculateCurrentPage();
      },err=>{
        console.log(err);
      }
    );
  }
  supprimer(id : any){
    let conf = confirm("Êtes-vous sûr(e) ?");
    if (conf === false) return;
    this.questionService.delete(id).subscribe(
      res=>{
        window.location.reload();
      },
      err=>{
        window.location.reload();
      }
    );
  }

  startIndex: number = 0;
  endIndex: number = 10;
  totalPages: number = 0;
  currentPage: number = 0;

  
  getItems(): any[] {
    return this.questions.slice(this.startIndex, this.endIndex);
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
    this.totalPages = Math.ceil(this.questions.length / 10);
  }

  calculateCurrentPage() {
    const elementsPerPage = 10; 
    this.currentPage = Math.min(Math.ceil(this.endIndex / elementsPerPage), Math.ceil(this.questions.length / elementsPerPage));
  }
  

}

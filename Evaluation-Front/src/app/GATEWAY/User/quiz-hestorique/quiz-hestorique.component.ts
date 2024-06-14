import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz-hestorique',
  templateUrl: './quiz-hestorique.component.html',
  styleUrl: './quiz-hestorique.component.css'
})
export class QuizHestoriqueComponent implements OnInit{
  
  constructor(private userService : UserServiceService,private router: Router){}
  Quiz:any=[];
  username:any;

  startIndex: number = 0;
  endIndex: number = 10;
  totalPages: number = 0;
  currentPage: number = 0;
  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')
    this.userService.hisQuiz(this.username).subscribe(
      res=>{
        console.log(res);
        this.Quiz=res;
        this.calculateTotalPages();
        this.calculateCurrentPage();
      },
      err=>{
        console.log(err);
      }
    );
  }

  

  idResponse(id: any) {
    return this.userService.idresponse(this.username, id).subscribe(
      (res: any) => {
        console.log(res)
        this.router.navigate(['/resultat', res]);
      },
      err => {
        console.log(err);
      }
    );;
  }
  
 


  getItems() {
    if (this.Quiz && this.Quiz.length > 0) {
      return this.Quiz.slice(0, 10); // Assurez-vous que this.histories est défini et non vide
    }
    return [];
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
    this.totalPages = Math.ceil(this.Quiz.length / 10);
  }

  calculateCurrentPage() {
    const elementsPerPage = 10; 
    this.currentPage = Math.min(Math.ceil(this.endIndex / elementsPerPage), Math.ceil(this.Quiz.length / elementsPerPage));
  }

}

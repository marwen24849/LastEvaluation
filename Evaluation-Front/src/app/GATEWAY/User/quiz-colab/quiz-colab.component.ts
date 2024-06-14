import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';


@Component({
  selector: 'app-quiz-colab',
  templateUrl: './quiz-colab.component.html',
  styleUrl: './quiz-colab.component.css'
})
export class QuizColabComponent implements OnInit{
  constructor(private userService : UserServiceService){}
  Quiz:any;
  username:any;
  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')
    this.userService.QuizCollab(this.username).subscribe(
      res=>{
        console.log(res);
        this.Quiz=res;
      },
      err=>{
        console.log(err);
      }
    );
  }
}

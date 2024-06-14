import { Component, OnInit } from '@angular/core';
import { ReponseServiceService } from '../reponse-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizServiceService } from '../../Qcm/quiz-service.service';
import { UserServiceService } from '../../../User/user-service.service';

@Component({
  selector: 'app-ajout-reponse',
  templateUrl: './ajout-reponse.component.html',
  styleUrl: './ajout-reponse.component.css'
})
export class AjoutReponseComponent implements OnInit{

  constructor(private service : ReponseServiceService, private quizService : QuizServiceService, private route : ActivatedRoute, private router : Router, private userService : UserServiceService){}

  id_Quiz: any;
  quiz: any;
  questions : any =[];
  responses : any =[];
  username:any;
  ngOnInit(): void {
    this.username = sessionStorage.getItem('username');
    this.route.params.subscribe(p=>{this.id_Quiz=p['id']});
    this.quizService.getById(this.id_Quiz).subscribe(
      res=>{
        this.quiz=res;
        this.questions = this.quiz.questions
        console.log(this.questions)
      },
      err=>{
        console.log(err);
      }
    );
  }

  submitAllResponses() {
    const model = {
      responses: this.responses,
      quiz: this.quiz
    };
  
    this.service.add(model).subscribe(
      (response: any) => {
        console.log("Réponses sauvegardées avec succès :", response);
        this.userService.ResponseQuiz(this.username, response.quiz.id, response.id).subscribe(
          res=>{
            console.log(res);
          },
          err=>{
            console.log(err);
          }
        );

        
        this.userService.updateQuiz(this.username,this.id_Quiz).subscribe(
          res=>{
            console.log(res);
            if(response.resultat.resultat){
              if(response.quiz.badge){
                this.userService.addBadgeCollab(this.username,response.quiz.id_badge).subscribe(
                  res=>{
                    console.log(res);
                  },
                  err=>{
                    console.log(err);
                  }
                );
                console.log(response.quiz.id_badge)
              }
            }
          },
          err=>{
            console.log(err);
          }
        );
        this.router.navigate(['/resultat',response.id])
      },
      (error: any) => {
        console.error("Erreur lors de la sauvegarde des réponses :", error);
      }
    );
  }
  
  updateResponse(question: any, event: any) {
    const answer = event.target.value;
    const existingResponseIndex = this.responses.findIndex((response: any) => response.question === question.questionTitle);

  if (existingResponseIndex !== -1) {
    this.responses.splice(existingResponseIndex, 1);
  }

  const response = {
    question: question.questionTitle,
    answer: answer
  };
  this.responses.push(response);
  console.log(this.responses)
}
  


}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GatewayServiceService } from '../gateway-service.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private serviceGateway : GatewayServiceService, private http : HttpClient) { }

  public api_User = this.serviceGateway.api+"/MICRO-SERVICE-USER/api/integration/";

  public AllUser(){
    return this.http.get(this.api_User+"users/Al");
  }

  public AffecterQuiz(id_quiz:any, id_user:any){
    return this.http.post(this.api_User+'monter/Quiz/'+id_quiz+'/User/'+id_user,id_quiz);
  }

  public findByUsername(username:String){
    return this.http.get(this.api_User+'users/name/'+username);
  }

  public quizUser(id_user:any){
    return this.http.get(this.api_User+"monter/QuizUser/"+id_user)
  }

  public HisFormation(name:any){
    return this.http.get(this.api_User+"colloborateurs/formations/"+name);
  }

  public listFormation(name:any){
    return this.http.get(this.api_User+"colloborateurs/formations/available/"+name);
  }

  public inscritformation(body:any){
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });
    return this.http.post(this.api_User+"colloborateurs/inscriptionFormation", body, { headers: headers })
  }

  public QuizCollab(name:any){
    return this.http.get(this.api_User+"colloborateurs/QuizUser/"+name);
  }

  public hisQuiz(name:any){
    return this.http.get(this.api_User+"colloborateurs/Quiz/"+name);
  }

  public updateQuiz(username:any, id_quiz:any){
    return this.http.get(this.api_User+"colloborateurs/"+username+"/Quiz/"+id_quiz);
  }

  ajouterCompetence(username: string, id: number) {
    return this.http.post(this.api_User + 'collaborateurs/ajouter-competence?username=' + username + '&id=' + id, null);
  }

  getCompetencesByUsername(username: string) {
    return this.http.get(this.api_User + 'colloborateurs/competences/' + username);
  }

  getAllCompetencesExceptUserSubscribed(username: string) {
    return this.http.get(this.api_User + 'collaborateurs/competences-disponibles/' + username);
  }

  public addBadgeCollab(username:any, id:any){
    return this.http.get(this.api_User+"colloborateurs/"+username+"/Badge/"+id);
  }

  public badgeColab(name:any){
    return this.http.get(this.api_User+"colloborateurs/"+name+"/badges");
  }

  public ResponseQuiz(username:any, id_quiz:any, id_res:any){
    return this.http.get(this.api_User+"colloborateurs/"+username+"/Quiz/"+id_quiz+"/Response/"+id_res);
  }

  public idresponse(username:any, id_quiz:any){
    return this.http.get(this.api_User+"colloborateurs/name/"+username+"/Quiz/"+id_quiz, { responseType: 'text' });
  }

}

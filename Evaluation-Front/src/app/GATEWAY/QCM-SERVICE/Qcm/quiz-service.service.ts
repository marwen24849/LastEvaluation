import { Injectable } from '@angular/core';
import { QcmServiceService } from '../qcm-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuizServiceService {

  constructor(private service : QcmServiceService, private http : HttpClient) { }

  private api_Quiz = this.service.api_qcm+"Quiz/";

  public ajout(Quiz: any){
    return this.http.post(this.api_Quiz+'All',Quiz);
  }

  

  public update(id: number, quiz: any) {
    return this.http.put(this.api_Quiz + id, quiz);
  }

  public updateQuizQus(id: number, id_Question: any) {
    return this.http.put(this.api_Quiz + id + '/AddQuestion/'+id_Question, id_Question);
  }

  public delete(id: number, questions:any) {
    const options = {
      headers: new HttpHeaders({
          'Content-Type': 'application/json'
      }),
      body: questions
  };
  return this.http.delete(this.api_Quiz + id, options);
  }

  public deleteQues(id_Quiz: number, id_Question: number) {
    return this.http.delete(this.api_Quiz + id_Quiz+'/Question/'+id_Question);
  }


  
  public getById(id: number) {
    return this.http.get(this.api_Quiz + id);
  }

  public getByCategories(cat: any) {
    return this.http.get(this.api_Quiz +'Cat/' + cat);
  }

  public getAll() {
    return this.http.get(this.api_Quiz + 'All');
  }

  public getQuestionQuiz(idquiz:any){
    return this.http.get(this.api_Quiz+"Question/"+idquiz);
  }

  
}

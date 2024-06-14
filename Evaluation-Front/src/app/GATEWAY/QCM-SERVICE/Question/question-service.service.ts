import { Injectable } from '@angular/core';
import { QcmServiceService } from '../qcm-service.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuestionServiceService {

  constructor(private service : QcmServiceService, private http : HttpClient) { }

  private api_Question = this.service.api_qcm+"Question/";

  public add(question : any ){
    return this.http.post(this.api_Question+'Add',question);
  }
  public update(id: number, question: any) {
    return this.http.put(this.api_Question + id, question);
  }

  public nbQuestion(cat:any,level:any){
    return this.http.get(this.api_Question+"cat/"+cat+"/level/"+level);
  }

  public delete(id: number) {
    return this.http.delete(this.api_Question + id);
  }

  public getById(id: number) {
    return this.http.get(this.api_Question + id);
  }

  public getAll() {
    return this.http.get(this.api_Question + 'All');
  }

  public getByCategory(category: string) {
    return this.http.get(this.api_Question + 'cat/' + category);
  }

  public getQuestionlevel(level: string){
    return this.http.get(this.api_Question + 'level/' + level);
  }

}

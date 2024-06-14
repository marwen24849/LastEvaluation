import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EquipeServiceService {

  
  private api_User = 'http://localhost:8887/MICRO-SERVICE-USER/api/integration/';

  constructor(private http: HttpClient) { }

  createEquipe(equipeModel: any) {
    return this.http.post(`${this.api_User}equipe/create`, equipeModel);
  }

  getEquipeById(equipeId: number) {
    return this.http.get(`${this.api_User}equipe/${equipeId}`);
  }

  getQuizEquipeById(equipeId: number) {
    return this.http.get(`${this.api_User}equipe/Quiz/${equipeId}`);
  }

  getEquipeByname(name: any) {
    return this.http.get(`${this.api_User}equipe/name/${name}`);
  }

  getEquipes() {
    return this.http.get(`${this.api_User}equipe`);
  }

  getUsersByEquipeId(equipeId: number) {
    return this.http.get(`${this.api_User}/equipe/${equipeId}/users`);
  }

  getUsersnoInscritEquipeId(equipeId: number) {
    return this.http.get(`${this.api_User}equipe/users/${equipeId}`);
  }

  addUserToEquipe(equipeId: number, userId: string) {
    return this.http.post(`${this.api_User}equipe/${equipeId}/addUser/${userId}`, {});
  }

  removeUserFromEquipe(equipeId: number, userId: string) {
    return this.http.delete(`${this.api_User}equipe/${equipeId}/removeUser/${userId}`);
  }

  addQuizToUsersEquipe(equipeId: number, id_quiz: number){
    return this.http.post(`${this.api_User}equipe/${equipeId}/addQuiz/${id_quiz}`, {});
  }
}

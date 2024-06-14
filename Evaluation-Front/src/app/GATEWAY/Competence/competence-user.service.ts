import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GatewayServiceService } from '../gateway-service.service';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CompetenceUserService {

  constructor(private serviceGateway : GatewayServiceService, private http : HttpClient) { }

  public baseUrl  = this.serviceGateway.api+"/MICRO-SERVICE-USER/api/integration/competence-users";

  getAllCompetenceUsers(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getAvailableCompetencesForUser(userId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/available/${userId}`);
  }

  getAvailableCompetences(userId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/DispoCom/${userId}`);
  }

  getCompetenceUserById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createCompetenceUser(competenceUser: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, competenceUser);
  }

  updateCompetenceUser(id: number, competenceUser: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, competenceUser);
  }

  deleteCompetenceUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}

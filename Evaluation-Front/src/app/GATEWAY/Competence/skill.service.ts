import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GatewayServiceService } from '../gateway-service.service';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SkillService {

  constructor(private serviceGateway : GatewayServiceService, private http : HttpClient) { }

  public baseUrl  = this.serviceGateway.api+"/MICRO-SERVICE-USER/api/integration/skills";

  getAllSkills(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getSkillById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createSkill(skill: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, skill);
  }

  updateSkill(id: number, skill: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, skill);
  }

  deleteSkill(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  searchSkillsByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/nom/{name}`);
  }
}

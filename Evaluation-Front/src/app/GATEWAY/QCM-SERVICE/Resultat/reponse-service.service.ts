import { Injectable } from '@angular/core';
import { QcmServiceService } from '../qcm-service.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReponseServiceService {

  constructor(private service : QcmServiceService, private http : HttpClient) { }

  private api = this.service.api_qcm+"Response/";

  public add(response : any ){
    return this.http.post(this.api+'Add',response);
  }
  public update(id: number, response: any) {
    return this.http.put(this.api + id, response);
  }

  public delete(id: number) {
    return this.http.delete(this.api + id);
  }

  public getById(id: number) {
    return this.http.get(this.api + id);
  }

  public getAll() {
    return this.http.get(this.api + 'All');
  }

  


}

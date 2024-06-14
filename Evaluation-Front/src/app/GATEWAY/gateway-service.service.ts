import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GatewayServiceService {

  constructor() { }

  public api = "http://localhost:8887";


}

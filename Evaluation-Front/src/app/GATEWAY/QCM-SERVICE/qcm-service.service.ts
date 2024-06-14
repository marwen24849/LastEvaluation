import { Injectable } from '@angular/core';
import { GatewayServiceService } from '../gateway-service.service';

@Injectable({
  providedIn: 'root'
})
export class QcmServiceService {

  constructor(private serviceGateway : GatewayServiceService) { }

  public api_qcm = this.serviceGateway.api+"/MICRO-SERVICE-QCM/";

}

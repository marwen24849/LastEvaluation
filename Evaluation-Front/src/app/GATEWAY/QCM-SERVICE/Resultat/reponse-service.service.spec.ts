import { TestBed } from '@angular/core/testing';

import { ReponseServiceService } from './reponse-service.service';

describe('ReponseServiceService', () => {
  let service: ReponseServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReponseServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

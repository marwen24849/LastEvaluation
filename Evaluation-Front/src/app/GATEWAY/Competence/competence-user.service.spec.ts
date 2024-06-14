import { TestBed } from '@angular/core/testing';

import { CompetenceUserService } from './competence-user.service';

describe('CompetenceUserService', () => {
  let service: CompetenceUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompetenceUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { KeycloakServiceUserService } from './keycloak-service-user.service';

describe('KeycloakServiceUserService', () => {
  let service: KeycloakServiceUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KeycloakServiceUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

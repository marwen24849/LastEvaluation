import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCompetenceComponent } from './user-competence.component';

describe('UserCompetenceComponent', () => {
  let component: UserCompetenceComponent;
  let fixture: ComponentFixture<UserCompetenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserCompetenceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserCompetenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

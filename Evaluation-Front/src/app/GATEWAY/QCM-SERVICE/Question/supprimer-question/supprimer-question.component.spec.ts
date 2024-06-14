import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupprimerQuestionComponent } from './supprimer-question.component';

describe('SupprimerQuestionComponent', () => {
  let component: SupprimerQuestionComponent;
  let fixture: ComponentFixture<SupprimerQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SupprimerQuestionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SupprimerQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

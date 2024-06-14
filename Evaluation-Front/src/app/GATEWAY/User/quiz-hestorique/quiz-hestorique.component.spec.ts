import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizHestoriqueComponent } from './quiz-hestorique.component';

describe('QuizHestoriqueComponent', () => {
  let component: QuizHestoriqueComponent;
  let fixture: ComponentFixture<QuizHestoriqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuizHestoriqueComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuizHestoriqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

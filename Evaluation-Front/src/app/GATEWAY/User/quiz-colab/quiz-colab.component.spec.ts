import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizColabComponent } from './quiz-colab.component';

describe('QuizColabComponent', () => {
  let component: QuizColabComponent;
  let fixture: ComponentFixture<QuizColabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuizColabComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuizColabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

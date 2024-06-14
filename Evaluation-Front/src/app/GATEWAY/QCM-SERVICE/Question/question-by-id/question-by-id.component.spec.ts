import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionByIdComponent } from './question-by-id.component';

describe('QuestionByIdComponent', () => {
  let component: QuestionByIdComponent;
  let fixture: ComponentFixture<QuestionByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuestionByIdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuestionByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

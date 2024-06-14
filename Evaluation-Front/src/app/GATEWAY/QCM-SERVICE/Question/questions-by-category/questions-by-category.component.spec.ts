import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionsByCategoryComponent } from './questions-by-category.component';

describe('QuestionsByCategoryComponent', () => {
  let component: QuestionsByCategoryComponent;
  let fixture: ComponentFixture<QuestionsByCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuestionsByCategoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuestionsByCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

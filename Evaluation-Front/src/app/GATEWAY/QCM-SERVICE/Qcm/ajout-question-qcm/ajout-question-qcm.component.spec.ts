import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutQuestionQcmComponent } from './ajout-question-qcm.component';

describe('AjoutQuestionQcmComponent', () => {
  let component: AjoutQuestionQcmComponent;
  let fixture: ComponentFixture<AjoutQuestionQcmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjoutQuestionQcmComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AjoutQuestionQcmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

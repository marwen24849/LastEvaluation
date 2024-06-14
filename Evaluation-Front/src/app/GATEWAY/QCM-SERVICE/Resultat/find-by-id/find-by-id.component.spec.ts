import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindByIdComponent } from './find-by-id.component';

describe('FindByIdComponent', () => {
  let component: FindByIdComponent;
  let fixture: ComponentFixture<FindByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FindByIdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FindByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

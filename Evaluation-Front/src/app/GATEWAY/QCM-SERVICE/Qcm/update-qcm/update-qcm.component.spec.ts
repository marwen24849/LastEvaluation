import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateQcmComponent } from './update-qcm.component';

describe('UpdateQcmComponent', () => {
  let component: UpdateQcmComponent;
  let fixture: ComponentFixture<UpdateQcmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateQcmComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateQcmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

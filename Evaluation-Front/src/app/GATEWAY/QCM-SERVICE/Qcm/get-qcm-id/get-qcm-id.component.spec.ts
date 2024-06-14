import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetQcmIdComponent } from './get-qcm-id.component';

describe('GetQcmIdComponent', () => {
  let component: GetQcmIdComponent;
  let fixture: ComponentFixture<GetQcmIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetQcmIdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetQcmIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

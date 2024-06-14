import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListQcmComponent } from './list-qcm.component';

describe('ListQcmComponent', () => {
  let component: ListQcmComponent;
  let fixture: ComponentFixture<ListQcmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListQcmComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListQcmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

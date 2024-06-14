import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListResultatComponent } from './list-resultat.component';

describe('ListResultatComponent', () => {
  let component: ListResultatComponent;
  let fixture: ComponentFixture<ListResultatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListResultatComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListResultatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

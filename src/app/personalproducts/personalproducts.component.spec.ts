import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalproductsComponent } from './personalproducts.component';

describe('PersonalproductsComponent', () => {
  let component: PersonalproductsComponent;
  let fixture: ComponentFixture<PersonalproductsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PersonalproductsComponent]
    });
    fixture = TestBed.createComponent(PersonalproductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

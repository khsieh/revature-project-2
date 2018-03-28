import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PwEmailComponent } from './pw-email.component';

describe('PwEmailComponent', () => {
  let component: PwEmailComponent;
  let fixture: ComponentFixture<PwEmailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PwEmailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PwEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

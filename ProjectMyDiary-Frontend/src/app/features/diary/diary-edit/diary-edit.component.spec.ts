import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiaryEditComponent } from './diary-edit.component';

describe('DiaryEditComponent', () => {
  let component: DiaryEditComponent;
  let fixture: ComponentFixture<DiaryEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiaryEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiaryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

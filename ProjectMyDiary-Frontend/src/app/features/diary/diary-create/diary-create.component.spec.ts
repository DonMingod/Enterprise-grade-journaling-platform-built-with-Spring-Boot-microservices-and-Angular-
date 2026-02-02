import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiaryCreateComponent } from './diary-create.component';

describe('DiaryCreateComponent', () => {
  let component: DiaryCreateComponent;
  let fixture: ComponentFixture<DiaryCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiaryCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiaryCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorResponseDtoComponent } from './error-response-dto.component';

describe('ErrorResponseDtoComponent', () => {
  let component: ErrorResponseDtoComponent;
  let fixture: ComponentFixture<ErrorResponseDtoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ErrorResponseDtoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrorResponseDtoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

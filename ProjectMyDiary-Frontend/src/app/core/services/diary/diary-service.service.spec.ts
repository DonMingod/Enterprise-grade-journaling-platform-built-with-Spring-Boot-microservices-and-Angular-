import { TestBed } from '@angular/core/testing';

import { DiaryServiceService } from './diary-service.service';

describe('DiaryServiceService', () => {
  let service: DiaryServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DiaryServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

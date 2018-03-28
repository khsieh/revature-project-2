import { TestBed, inject } from '@angular/core/testing';

import { ResetPwService } from './reset-pw.service';

describe('ResetPwService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ResetPwService]
    });
  });

  it('should be created', inject([ResetPwService], (service: ResetPwService) => {
    expect(service).toBeTruthy();
  }));
});

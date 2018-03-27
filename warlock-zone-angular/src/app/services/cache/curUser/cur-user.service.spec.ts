import { TestBed, inject } from '@angular/core/testing';

import { CurUserService } from './cur-user.service';

describe('CurUserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CurUserService]
    });
  });

  it('should be created', inject([CurUserService], (service: CurUserService) => {
    expect(service).toBeTruthy();
  }));
});

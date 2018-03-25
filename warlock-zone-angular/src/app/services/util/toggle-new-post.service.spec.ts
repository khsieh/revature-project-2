import { TestBed, inject } from '@angular/core/testing';

import { ToggleNewPostService } from './toggle-new-post.service';

describe('ToggleNewPostService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ToggleNewPostService]
    });
  });

  it('should be created', inject([ToggleNewPostService], (service: ToggleNewPostService) => {
    expect(service).toBeTruthy();
  }));
});

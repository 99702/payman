import { TestBed } from '@angular/core/testing';

import { RedirectorGuard } from './redirector.guard';

describe('RedirectorGuard', () => {
  let guard: RedirectorGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(RedirectorGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});

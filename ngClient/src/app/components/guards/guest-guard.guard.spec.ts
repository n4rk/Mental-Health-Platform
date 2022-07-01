import { TestBed } from '@angular/core/testing';

import { GuestGuardGuard } from './guest-guard.guard';

describe('GuestGuardGuard', () => {
  let guard: GuestGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(GuestGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});

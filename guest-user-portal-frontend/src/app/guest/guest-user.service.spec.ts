/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { GuestUserService } from './guest-user.service';

describe('GuestUserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GuestUserService]
    });
  });

  it('should ...', inject([GuestUserService], (service: GuestUserService) => {
    expect(service).toBeTruthy();
  }));
});

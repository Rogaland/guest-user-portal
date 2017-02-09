/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { GuestUserAdminService } from './guest-user-admin.service';

describe('GuestUserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GuestUserAdminService]
    });
  });

  it('should ...', inject([GuestUserAdminService], (service: GuestUserAdminService) => {
    expect(service).toBeTruthy();
  }));
});

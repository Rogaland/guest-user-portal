/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { EmployeeSearchService } from './employee-search.service';

describe('EmployeeSearchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmployeeSearchService]
    });
  });

  it('should ...', inject([EmployeeSearchService], (service: EmployeeSearchService) => {
    expect(service).toBeTruthy();
  }));
});

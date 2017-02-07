import { EmployeeSearch } from 'app/shared/employee-search';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { FormDataService } from '../form-data.service';
import { EmployeeSearchService } from 'app/shared/employee-search.service';

@Component({
  selector: 'app-guest-host',
  templateUrl: './guest-host.component.html',
  styleUrls: ['./guest-host.component.css']
})
export class GuestHostComponent implements OnInit, OnDestroy {

  @Input() formData;

  private visiting: EmployeeSearch[];

  constructor(private formDataService: FormDataService,
              private employeeSearchService: EmployeeSearchService) { }

  ngOnInit() {
    this.formData = this.formDataService.getData();
  }

  ngOnDestroy() {
    this.formDataService.setData(this.formData);
  }

  save() {
    console.log('saving');
    this.formDataService.create(this.formData).subscribe();
  }

  search() {
    const name = this.formData.owner;
    if (name && name.length > 3) {
      this.employeeSearchService.findUsers(name).subscribe(result => this.visiting = result);
    }
  }
}

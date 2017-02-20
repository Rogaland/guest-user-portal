import { ConfigService } from '../config.service';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { EmployeeSearch } from './employee-search';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class EmployeeSearchService {

  constructor(private http: Http,
  private config: ConfigService) { }

  findUsers(name: string): Observable<EmployeeSearch[]> {
      let url = this.config.baseUrl + 'api/employee?q=' + name;
      return this.http
        .get(url)
        .map(res => res.json()).catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error); // for demo purposes only
      return Promise.reject(error.message || error);
  }
}

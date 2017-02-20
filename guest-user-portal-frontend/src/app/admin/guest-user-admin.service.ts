import { ConfigService } from '../config.service';
import { GuestUserAdmin } from './guest-user-admin';
import { EventEmitter, Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class GuestUserAdminService {

  private _guests: number;
  private _guestsHistorical: number;
  public dataManipulated: EventEmitter<any> = new EventEmitter<any>();

  private baseUrl: string;
  constructor(private http: Http,
    private config: ConfigService) {
    this.baseUrl = this.config.baseUrl + 'api/admin/guest'
   }

  get guests() {
    return this._guests;
  }

  get guestsHistorical() {
    return this._guestsHistorical;
  }

  historizeGuests() {
    return this.http.post(this.baseUrl + '/today', null).map(() => this.dataManipulated.emit());
  }

  historizeAllGuests() {
    return this.http.post(this.baseUrl + '/historical', null).map(() => this.dataManipulated.emit());
  }

  getTodaysUsers(): Observable<GuestUserAdmin[]> {
      const url = this.baseUrl + '/today';
      return this.http
        .get(url)
        .map(res => {
          this._guests = res.json().length;
          return res.json();
        })
        .catch(this.handleError);
  }

  getHistoricalUsers(): Observable<GuestUserAdmin[]> {
      const url = this.baseUrl + '/historical';
      return this.http
        .get(url)
        .map(res => {
          this._guestsHistorical = res.json().length;
          return res.json();
        }).catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}

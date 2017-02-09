import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { GuestUser } from './guest-user';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class GuestUserService {

    private guestUser: GuestUser = new GuestUser();
    private _confirmationResult: any;

    constructor(private http: Http) {};

    set confirmationResult(co: any) {
        this._confirmationResult = co;
    }
    get confirmationResult() {
        return this._confirmationResult;
    }

    getData(): GuestUser {
        return this.guestUser;
    }

    setData(guestUser: GuestUser) {
        this.guestUser = guestUser;
    }

    clearData() {
        this.guestUser = new GuestUser();
    }

    create(notifyHost: boolean, notifyGuest: boolean, user: GuestUser): Observable<GuestUser> {
        const data = { 'notifyHost': notifyHost, 'notifyGuest': notifyGuest };
        const url = '/api/guest/user?' + this.toParam(data);
        const u = user || this.guestUser;
        return this.http
            .post(url, u)
            .map(res => res.json());
    }

    toParam (data) {
        return Object.keys(data).map(function (k) {
            return encodeURIComponent(k) + '=' + encodeURIComponent(data[k]);
        }).join('&');
    }
}

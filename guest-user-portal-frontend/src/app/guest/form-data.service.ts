import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { FormData } from './form-data';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class FormDataService {

    private formData: FormData = new FormData();
    private _confirmationResult: any;

    constructor(private http: Http) {};

    set confirmationResult(co: any) {
        this._confirmationResult = co;
    }
    get confirmationResult() {
        return this._confirmationResult;
    }

    getData(): FormData {
        return this.formData;
    }

    setData(formData: FormData) {
        this.formData = formData;
    }

    clearData() {
        this.formData = new FormData();
    }

    create(): Observable<FormData> {
        const url = '/api/guest/user?notifyHost=true';
        return this.http
            .post(url, this.formData)
            .map(res => res.json())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

}

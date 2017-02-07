import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { FormData } from './form-data';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class FormDataService {

    private formData: FormData = new FormData();

    constructor(private http: Http) { };

    getData(): FormData {
        return this.formData;
    }

    setData(formData: FormData) {
        console.log('setting form data: ' + JSON.stringify(formData));
        this.formData = formData;
    }

    create(data: any): Observable<FormData> {
        let url = '/api/guest/user';
        return this.http
            .post(url, data)
            .map(res => res.json().data)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

}

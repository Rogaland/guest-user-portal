import { Injectable } from '@angular/core';

@Injectable()
export class ConfigService {

  constructor() { }

  get baseUrl(): string {
    let baseurl = '/';
    if (electron && electron.remote && electron.remote.process) {
      if (electron.remote.process.argv.length > 2) {
        baseurl = electron.remote.process.argv[2];
        if (!baseurl.endsWith('/')) {
          baseurl = baseurl + '/';
        }
      }
    }
    return baseurl;
  }
}

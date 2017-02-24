import { Injectable } from '@angular/core';

@Injectable()
export class ConfigService {

  constructor() { }

  get isElectron(): boolean{
    return electron && electron.remote && electron.remote.process;
  }

  get isLocal(): boolean{
    if (this.isElectron) {
      return electron.remote.process.argv[0].indexOf('node_modules') > 0;
    }
    return false;
  }
  get baseUrl(): string {
    let baseurl = '/';
    if (this.isElectron) {
      let urlIndex = 1;
      if (this.isLocal) {
        urlIndex = 2;
      }
      if (electron.remote.process.argv.length > urlIndex) {
        baseurl = electron.remote.process.argv[urlIndex];
        if (!baseurl.endsWith('/')) {
          baseurl = baseurl + '/';
        }
      }
    }
    return baseurl;
  }
}

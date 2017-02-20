import { APP_BASE_HREF, HashLocationStrategy, LocationStrategy } from '@angular/common';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { environment } from './environments/environment';
import { AppModule } from './app/app.module';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule, {
  providers: [{ provide: LocationStrategy,  useClass: HashLocationStrategy}, { provide: APP_BASE_HREF, useValue: '/' }]
});

import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AdminComponent implements OnInit, OnDestroy {

  public isRegistered: boolean;
  private sub: any;
  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.sub = this.route.queryParams.subscribe(params => {
      this.isRegistered = params['alreadyRegistered'] === 'true';
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}

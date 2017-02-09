/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { GuestTodayComponent } from './guest-today.component';

describe('GuestTodayComponent', () => {
  let component: GuestTodayComponent;
  let fixture: ComponentFixture<GuestTodayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestTodayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestTodayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

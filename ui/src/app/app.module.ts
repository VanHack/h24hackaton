import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule } from 'angular-calendar';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';

import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { BiTSearchService } from './search.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserAnimationsModule,
    CalendarModule.forRoot(),
    FormsModule,
    HttpClientModule,
    HttpClientJsonpModule,
    NgbModule.forRoot(),
    NgbModalModule.forRoot()
  ],
  providers: [BiTSearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }

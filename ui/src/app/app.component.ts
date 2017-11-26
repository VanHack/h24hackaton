import { Component, ViewChild, TemplateRef } from '@angular/core';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Artist } from './artist';
import { Event } from './event';
import { SearchForm } from './searchform';

import { BiTSearchService } from './search.service';

import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours
} from 'date-fns';

import { Subject } from 'rxjs/Subject';

import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent
} from 'angular-calendar';


const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css' ]
})
export class AppComponent {
  
  @ViewChild('modalContent') modalContent: TemplateRef<any>;
  
  searchingForm = new SearchForm('');
  searching = false;
  error: String = null;
  
  artistInfo: Artist = null;

  viewDate: Date = new Date();
  activeDayIsOpen: boolean = false;
  isOpened: Array<Boolean> = null;
  rawEvents: Array<Event> = null;
  events: CalendarEvent<Event>[] = [];
  
  modalData: CalendarEvent<Event>;

  refresh: Subject<any> = new Subject();



  constructor(private searchService: BiTSearchService, private modal: NgbModal) {}
  
  
  runSearch() {
    this.searching = true;
    this.error = null;
    this.artistInfo = null;
    this.events = null;
    this.searchService.search(this.searchingForm.artistName).subscribe(
      artist => {
        this.artistInfo = artist;
        this.searching = false;
      },
      err => {
        this.error = err.error;
        this.searching = false;
      }
    );
  }
  
  loadEvents() {
    let self = this;
    this.searching = true;
    this.searchService.loadEvents(this.searchingForm.artistName).subscribe(
      events => {
        this.events = [];
        events.forEach( function(item, index) { 
          let evt = { start: new Date(item['datetime']), 
                      //end: item['datetime'], 
                      allDay: true,
                      title: item['venue']['city'] + ', ' + item['venue']['country'],
                      color: colors.red,
                      meta: item };
          self.events.push(evt);
        });
        console.log(this.events);
        this.rawEvents = events;
        this.isOpened = new Array<Boolean>(this.rawEvents.length);
        this.refresh.next();
        this.searching = false;
      },
      err => {
        this.error = err.error;
        this.searching = false;
      }
    );
  }
  
  displayDay({ date, events }: { date: Date; events: CalendarEvent<Event>[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
        this.viewDate = date;
      }
    }
  }
  
  displayEventDetails(event: CalendarEvent<Event>): void {
    this.modalData = event;
    this.modal.open(this.modalContent, { size: 'lg' });
  }

}

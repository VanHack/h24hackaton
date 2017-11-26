import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map';

import { Artist } from './artist';
import { Event } from './event';


@Injectable()
export class BiTSearchService {

  search_url = 'http://localhost:9000/api/artist/info.json';
  events_url = 'http://localhost:9000/api/artist/events.json';
  
  constructor(private httpClient: HttpClient) {}

  search(artistName: string): Observable<Artist> {
    return this.httpClient.get<Artist>(this.search_url, {
                  params: this.buildParams(artistName)  
           });
  }
  
  loadEvents(artistName: string): Observable<Event[]> {
    return this.httpClient.get<Array<Event>>(this.events_url, {
                  params: this.buildParams(artistName)
           });
  }

  buildParams(artistName) { 
      return new HttpParams().append('artistName', artistName);
  }
}

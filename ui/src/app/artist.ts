export class Artist {
  
  constructor(public id: number, public name: string, public url: string, public image_url: string,
              public thumb_url: string, public facebook_page_url: string, public mbid: string, 
              public tracker_count: number, public upcoming_event_count: number) {}
}

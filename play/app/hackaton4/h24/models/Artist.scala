package hackaton4.h24.models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Artist(id: String, name: String, url: Option[String], facebookUrl: Option[String],
                  imageUrl: Option[String], thumbUrl: Option[String], mbId: Option[String],
                  eventCount: Option[Int], trackerCount: Option[Int])

object Artist {

    implicit val JSON_FORMATTER: Format[Artist] = (
        (__ \ "id").format[String]  ~
        (__ \ "name").format[String] ~
        (__ \ "url").formatNullable[String] ~
        (__ \ "image_url").formatNullable[String] ~
        (__ \ "thumb_url").formatNullable[String] ~
        (__ \ "facebook_page_url").formatNullable[String] ~
        (__ \ "mbid").formatNullable[String] ~
        (__ \ "tracker_count").formatNullable[Int] ~
        (__ \ "upcoming_event_count").formatNullable[Int]
    )(Artist.apply _, unlift(Artist.unapply))
}


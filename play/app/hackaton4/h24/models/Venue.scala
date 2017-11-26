package hackaton4.h24.models

import play.api.libs.functional.syntax._
import play.api.libs.json._


case class Venue(name: String, latitude: Option[String], longitude: Option[String], city: String,
                 region: Option[String], country: String)

object Venue {

    implicit val JSON_FORMATTER: Format[Venue] = (
        (__ \ "name").format[String]  ~
        (__ \ "latitude").formatNullable[String] ~
        (__ \ "longitude").formatNullable[String] ~
        (__ \ "city").format[String] ~
        (__ \ "region").formatNullable[String] ~
        (__ \ "country").format[String]
    )(Venue.apply _, unlift(Venue.unapply))
}
package hackaton4.h24.models

import java.io.Writer

import play.api.libs.functional.syntax._
import play.api.libs.json._


case class Offer(offerType: String, url: Option[String], status: String)

object Offer {

    implicit val JSON_FORMATTER: Format[Offer] = (
        (__ \ "type").format[String]  ~
        (__ \ "url").formatNullable[String] ~
        (__ \ "status").format[String]
        )(Offer.apply _, unlift(Offer.unapply))

    implicit val JSON_SEQ_FORMATTER = Format(Reads.seq(JSON_FORMATTER), Writes.seq(JSON_FORMATTER))
}
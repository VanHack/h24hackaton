package hackaton4.h24.models

import java.time.LocalDateTime
import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Event(id: String, artistId: String, url: Option[String], onSale: Option[LocalDateTime],
                 dateTime: LocalDateTime, venue: Option[Venue], offers: Seq[Offer], lineup: Seq[String])

object Event {

    implicit val JSON_FORMATTER: Format[Event] = (
        (__ \ "id").format[String]  ~
        (__ \ "artist_id").format[String] ~
        (__ \ "url").formatNullable[String] ~
        (__ \ "on_sale_datetime").format[String] ~
        (__ \ "datetime").format[LocalDateTime] ~
        (__ \ "venue").formatNullable[Venue](Venue.JSON_FORMATTER) ~
        (__ \ "offers").format[Seq[Offer]](Offer.JSON_SEQ_FORMATTER) ~
        (__ \ "lineup").format[Seq[String]]
    )(jsonApply _, unlift(jsonUnapply))

    private[this] def jsonApply(id: String, artistId: String, url: Option[String], onSale: String,
                                dateTime: LocalDateTime, venue: Option[Venue], offers: Seq[Offer], lineup: Seq[String]): Event = {
        var onSaleDateTime: Option[LocalDateTime] = None
        try {
            onSaleDateTime = Some(LocalDateTime.parse(onSale))
        } catch {
            case ex: Exception => // do nothing
        }
        Event(id, artistId, url, onSaleDateTime, dateTime, venue, offers, lineup)
    }

    private[this] def jsonUnapply(x: Event): Option[(String, String, Option[String], String, LocalDateTime,
                                                     Option[Venue], Seq[Offer], Seq[String])] = {
        Some((x.id, x.artistId, x.url, x.onSale.map(_.toString).getOrElse(""), x.dateTime, x.venue, x.offers, x.lineup))
    }

    implicit val JSON_SEQ_FORMATTER = Format(Reads.seq(JSON_FORMATTER), Writes.seq(JSON_FORMATTER))
}
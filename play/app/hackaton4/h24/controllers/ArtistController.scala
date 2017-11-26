package hackaton4.h24.controllers

import javax.inject.Inject

import hackaton4.h24.services.IBiTSearchService
import hackaton4.h24.models._
import play.api.Logger
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

class ArtistController @Inject()(components: ControllerComponents, bitService: IBiTSearchService)
    extends AbstractController(components) {


    def loadInfo = Action.async { implicit request =>
        // retrieving the 'artistName' GET query parameter
        request.getQueryString("artistName").map { artistName =>
            // call BandsInTown web service
            bitService.search(artistName).map { infoFound =>
                infoFound.map { info =>
                    Ok(Json.toJson(info)(Artist.JSON_FORMATTER))
                } getOrElse {
                    BadRequest(s"We don't know who ${artistName} is. Sorry! :(")
                }
            } recover {
                case ex: Throwable =>
                    Logger.error("Error while ArtistController.loadInfo(): ", ex)
                    BadRequest(s"We don't know who ${artistName} is. Sorry! :(")
            }
        } getOrElse {
            Future.successful(BadRequest("Request query parameter 'artistName' must be set."))
        }
    }

    def listEvents = Action.async { implicit request =>
        // retrieving the 'artistName' GET query parameter
        request.getQueryString("artistName").map { artistName =>
            // call BandsInTown web service
            bitService.events(artistName).map { info =>
                Ok(Json.toJson(info)(Event.JSON_SEQ_FORMATTER))
            } recover {
                case ex: Throwable =>
                    Logger.error("Error while ArtistController.listEvents(): ", ex)
                    BadRequest(s"We could not load the events for ${artistName}. Sorry! :(")
            }
        } getOrElse {
            Future.successful(BadRequest("Request query parameter 'artistName' must be set."))
        }
    }
}

package hackaton4.h24.services

import javax.inject.Inject

import hackaton4.h24.models.{Artist, Event}
import play.api.cache.{AsyncCacheApi, SyncCacheApi}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.{Configuration, Logger}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

trait IBiTSearchService {
    def search(artistName: String): Future[Option[Artist]]
    def events(artistName: String): Future[Seq[Event]]
}


class BiTSearchService @Inject()(conf: Configuration, wsClient: WSClient, cache:  SyncCacheApi) extends IBiTSearchService {

    val BASE_URL: String = conf.get[String]("app.bitApiUrl")


    override def search(artistName: String) = {
        val url: String = s"${BASE_URL}${artistName}"
        Logger.debug(s"Searching information about ${artistName} using URL: ${url}")
        // let's first check if the artist is in our temporary cache
        cache.get[Artist](artistName)
            .map(cachedInfo => Future.successful(Some(cachedInfo)))
            .getOrElse {
                // if not, then query bands in town webservice
                this.prepareGet(url).get()
                    .map { response =>
                        Logger.debug(response.body)
                        val info = Json.fromJson(response.json)(Artist.JSON_FORMATTER).asOpt
                        info.map { i =>
                            // if found, add to cache and leave it there for 1 hour
                            cache.set(artistName, i, 1.hour)
                            i
                        }
                    }
            }
    }

    override def events(artistName: String) = {
        val url: String = s"${BASE_URL}${artistName}/events"
        // let's first check if we have already loaded the events for this artist recently
        cache.get[Seq[Event]](s"${artistName}_events")
            .map(cachedInfo => Future.successful(cachedInfo))
            .getOrElse {
                // if not, then query bands in town webservice
                this.prepareGet(url).get()
                    .map { response =>
                        Logger.debug(response.body)
                        val encoded = Json.fromJson(response.json)(Event.JSON_SEQ_FORMATTER)
                        // if found and there are no JSON enconding errors, add to cache and leave it there for 1 hour
                        if (!encoded.isError) {
                            cache.set(s"${artistName}_events", encoded.get, 1.hour)
                        }
                        encoded.get
                    }
            }
    }

    private[this] def prepareGet(url: String) = {
      wsClient.url(url).addQueryStringParameters("app_id" -> "123456789")
    }
}

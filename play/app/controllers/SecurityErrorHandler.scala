package controllers

import javax.inject._

import play.api.{Configuration, OptionalSourceMapper}
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router

import scala.concurrent.Future


class SecurityErrorHandler @Inject() (env: play.api.Environment, config: Configuration,
                                      sourceMapper: OptionalSourceMapper, router: javax.inject.Provider[Router])
        extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

}

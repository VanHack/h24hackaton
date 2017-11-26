package modules

import com.google.inject.AbstractModule
import hackaton4.h24.services._
import net.codingwell.scalaguice.ScalaModule




class ServicesModule extends AbstractModule with ScalaModule {
    override def configure = {
        // services
        bind[IBiTSearchService].to[BiTSearchService]
        ()
    }
}

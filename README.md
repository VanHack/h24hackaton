# Bands in Town search challenge

This project was created as part of the 4th VanHackaton (www.vanhack.com). 

The tecnologies used during its implementation were: 
<ul>
  <li> [angular.io](https://github.com/angular/angular-cli)
  <li> [Play!Framework](http://www.playframework.com)
  <li> Scala
</ul>

I decided to create a full stack application to take advantage of cross-users' caching and also to demonstrate my expertise as a solution architect.

I also provided a running environment online, at Amazon AWS. You can check it out at ...


## Running locally

To pre-reqs for running this application as a local development environment are [NodeJS with npm] (https://nodejs.org/en/download/) and the 
[sbt](https://www.playframework.com/documentation/2.6.x/Installing) for the backend. I have also provided a ZIP file with a runnable version of the
backend in the directory `dist`.

Once these pre-reqs are installed, the web module can be started using the command `ng serve` at the `ui` subdir. The backend module is started
issuing the command `sbt run` at the `play` subdir.


Have fun! :)
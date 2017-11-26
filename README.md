# Bands in Town search challenge

This project was created as part of the 4th VanHackaton (www.vanhack.com). 

The tecnologies used during its implementation were: 
<ul>
  <li> Angular.io (https://github.com/angular/angular-cli)
  <li> Play!Framework (http://www.playframework.com)
  <li> Scala
</ul>

I decided to create a full stack application because I can:
<ul>
  <li>take advantage of cross-users' caching,</li>
  <li>demonstrate a wider set of skills, and</li>
  <li>apply my solution architect expertise, since from an enterprise-scale application point of view building a multi-layer application is more robust.</li>
</ul>

## Running locally

To pre-reqs for running this application as a local development environment are NodeJS with npm (https://nodejs.org/en/download/) and the 
sbt (https://www.playframework.com/documentation/2.6.x/Installing) for the backend. I have also provided a ZIP file with a runnable version of the
backend in the directory `dist`.

Once these pre-reqs are installed, the web module can be started using the command `ng serve` at the `ui` subdir. The backend module is started
issuing the command `sbt run` at the `play` subdir.


Have fun! :)

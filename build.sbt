name := "JsonSeries"

version := "0.1"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "spray"               at "http://repo.spray.io/",
  "Big Bee Consultants" at "http://repo.bigbeeconsultants.co.uk/repo"
)

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"      % "2.0" % "test",
  "com.typesafe.play" %% "play-json"      % "2.2.1",
  "io.argonaut"       %% "argonaut"       % "6.0.3",
  "io.spray"          %%  "spray-json"    % "1.2.5",
  "net.liftweb"       %% "lift-json"      % "2.5.1",
  "org.json4s"        %% "json4s-native"  % "3.2.7",
  "org.json4s"        %% "json4s-jackson" % "3.2.7"
)

testOptions in Test += Tests.Argument("-oD")
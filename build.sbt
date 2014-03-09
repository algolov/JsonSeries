name := "JsonSeries"

version := "0.1"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "spray"               at "http://repo.spray.io/",
  "Big Bee Consultants" at "http://repo.bigbeeconsultants.co.uk/repo"
)

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"      % "2.0" % "test",
  "com.typesafe.play" %% "play-json"      % "2.2.1" withSources(),
  "io.argonaut"       %% "argonaut"       % "6.0.2" withSources() withJavadoc(),
  "io.spray"          %%  "spray-json"    % "1.2.5" withSources() withJavadoc(),
  "net.liftweb"       %% "lift-json"      % "2.5.1" withSources() withJavadoc(),
  "org.json4s"        %% "json4s-native"  % "3.2.7" withSources() withJavadoc(),
  "org.json4s"        %% "json4s-jackson" % "3.2.7" withSources() withJavadoc()
)

testOptions in Test += Tests.Argument("-oD")
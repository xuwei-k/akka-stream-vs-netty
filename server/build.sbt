scalaVersion := "2.11.2"

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)

shellPrompt := { state =>
  val branch = if(file(".git").exists){
    "git branch".lines_!.find{_.head == '*'}.map{_.drop(1)}.getOrElse("")
  }else ""
  Project.extract(state).currentRef.project + branch + " > "
}

enablePlugins(PlayScala)

def akkaHttpSettings = Seq(
  mainClass in Compile := Some("play.core.server.akkahttp.AkkaHttpServer"),
  libraryDependencies ++= (
    ("com.typesafe.play" %% "play-akka-http-server-experimental"  % play.core.PlayVersion.current) ::
    Nil
  ),
  libraryDependencies ~= {
    _.map(_.exclude("com.typesafe.play", "play-netty-utils"))
  }
)

sys.props("akka-http") match {
  case "true" => akkaHttpSettings
  case "false" => Nil
  case _ => sys.error("please specify akka-http=true or akka-http=false")
}


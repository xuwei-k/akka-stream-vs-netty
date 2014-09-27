scalacOptions ++= Seq("-deprecation", "-language:_", "-unchecked")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

sys.props("akka-http") match {
  case "true" =>
    addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4-20140928")
  case "false" =>
    addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.0-M1")
  case _ =>
   sys.error("please specify -Dakka-http=true or -Dakka-http=false")
}

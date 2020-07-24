import sbt._

object Zio {
  lazy val ZIO_VERSION = "1.0.0-RC21"

  lazy val dependency: ModuleID = 
    "dev.zio" %% "zio" % ZIO_VERSION

}

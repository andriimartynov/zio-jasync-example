import sbt._

object JasyncSql {
  lazy val JASYNC_VERSION = "1.1.3"

  lazy val coreDependencies: Seq[ModuleID] = Seq(
    "com.github.jasync-sql" % "jasync-common" % JASYNC_VERSION,
    "com.github.andriimartynov" %% "zio-jasync-sql" % "1.1.1",
  )

  lazy val mysqlDependency: ModuleID =
    "com.github.jasync-sql" % "jasync-mysql" % JASYNC_VERSION

  lazy val postgresqlDependency: ModuleID =
    "com.github.jasync-sql" % "jasync-postgresql" % JASYNC_VERSION
  
}

import sbt.Keys.name

lazy val mainConfig: Seq[Setting[_]] = Seq(
  conflictManager := ConflictManager.latestCompatible,
  version := "0.0.1",
  organization := "com.github.andriimartynov",
  scalaVersion := "2.12.11",
  startYear := Some(2020),
  resolvers += Resolver.jcenterRepo
)

lazy val root = (project in file("."))
  .aggregate(
    `zio-jasync-mysql-example`, 
    `zio-jasync-postgresql-example`)

lazy val `zio-jasync-mysql-example` = (project in file("zio-jasync-mysql-example"))
  .settings(name := "zio-jasync-mysql-example")
  .settings(mainConfig)
  .settings(
    libraryDependencies ++= JasyncSql.coreDependencies,
    libraryDependencies +=  JasyncSql.mysqlDependency,
    libraryDependencies +=  Slf4j.dependency,
    libraryDependencies +=  Zio.dependency
  )

lazy val `zio-jasync-postgresql-example` = (project in file("zio-jasync-postgresql-example"))
  .settings(name := "zio-jasync-postgresql-example")
  .settings(mainConfig)
  .settings(
    libraryDependencies ++= JasyncSql.coreDependencies,
    libraryDependencies +=  JasyncSql.postgresqlDependency,
    libraryDependencies +=  Slf4j.dependency,
    libraryDependencies +=  Zio.dependency
  )

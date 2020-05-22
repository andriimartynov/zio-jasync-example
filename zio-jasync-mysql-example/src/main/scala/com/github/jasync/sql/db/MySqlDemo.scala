package com.github.jasync.sql.db

import java.util.concurrent.TimeUnit

import com.github.jasync.sql.db.ConnectionService._
import com.github.jasync.sql.db.ScalaConverters._
import com.github.jasync.sql.db.general.ArrayRowData
import com.github.jasync.sql.db.mysql.MySQLConnectionBuilder
import org.slf4j.LoggerFactory
import zio._

object MySqlDemo extends App {
  private val logger = LoggerFactory.getLogger("Demo")

  logger.error("starting")
  logger.warn("starting warn")
  logger.info("starting info")
  logger.debug("starting debug")
  logger.trace("starting trace")

  val configuration: ConnectionPoolConfiguration =
    new ConnectionPoolConfiguration(
      "host.com",
      3306,
      "schema",
      "username",
      "password",
      100,
      TimeUnit.MINUTES.toMillis(15),
      10000, // maxQueueSize
      TimeUnit.SECONDS.toMillis(30)
    )

  val layer: Layer[Throwable, ConnectionService] =
    ConnectionService.live(MySQLConnectionBuilder
      .createConnectionPool(configuration))

  val myRequest: RIO[ConnectionService, Unit] = for {
    queryResult <- sendPreparedStatement("select * from test limit 2")
  } yield {
    logger.info(s"${queryResult.rows.head.asInstanceOf[ArrayRowData].getColumns.mkString(",")}")
    logger.info(s"${queryResult.rows.head.string(0)}")
  }

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] =
    myRequest.provideLayer(layer).fold(_ => 1, _ => 0)
}

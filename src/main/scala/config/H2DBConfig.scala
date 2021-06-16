package com.test.akka
package config

import slick.jdbc.H2Profile.api._


object H2DBConfig {

  val db = Database.forConfig("h2mem1")

}

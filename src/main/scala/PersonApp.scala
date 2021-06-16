package com.test.akka

import services._

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._

import scala.io.StdIn


object PersonApp {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem[Nothing](Behaviors.empty, "FirstScalaApp")
    implicit val ec = system.executionContext

    import config.JsonFormats._

    val routes = path("persons") {
      get {
        complete(PersonService.findAll)
      }
    }


    val binding = Http().newServerAt("localhost", 8080).bind(routes)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return

    binding.flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
package com.test.akka
package config

import repository.PersonRepository

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

object JsonFormats extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val personsJsonFormat = jsonFormat4(PersonRepository.Person)
}

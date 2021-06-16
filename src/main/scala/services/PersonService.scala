package com.test.akka
package services

import repository.PersonRepository
import repository.PersonRepository.Person

import akka.actor.typed.ActorRef


sealed trait Command

case class GetPersons(replyTo: ActorRef[Persons]) extends Command

case class Persons(persons: List[Person])

object PersonService {

  def findAll() = PersonRepository.findAll.mapTo[Person]
}

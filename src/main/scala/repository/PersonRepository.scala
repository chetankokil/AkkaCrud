package com.test.akka
package repository

import slick.jdbc.H2Profile.api._


object PersonRepository {

  import config.H2DBConfig._

  private val persons = TableQuery[Persons]

  def findAll() = db.run(persons.result).mapTo[Persons]

  case class Person(id: Long, name: String, age: Int, sex: Char)

  try {
    val setup = DBIO.seq(
      (persons.schema).create,

      persons += Person(1, "Chetan", 38, 'M'),
      persons += Person(2, "Arun", 40, 'M'),
      persons += Person(3, "Aruna", 32, 'F'),
    )
    val setupFuture = db.run(setup)
  } finally db.close()

  class Persons(tag: Tag) extends Table[Person](tag, "PERSONS") {
    def * = (id, name, age, sex) <> (Person.tupled, Person.unapply)

    def id = column[Long]("ID", O.PrimaryKey)

    def name = column[String]("NAME")

    def age = column[Int]("AGE")

    def sex = column[Char]("SEX")
  }

}

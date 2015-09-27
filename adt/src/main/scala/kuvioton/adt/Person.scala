package kuvioton.adt

case class Person(name: String, age: Int, food: Food)

sealed trait Food
case class Sandwich(bread: String, hasMayo: Boolean) extends Food
case class Sausage(length: Int) extends Food
case class Soup(volume: Float, spicy: Boolean, ingredients: List[String]) extends Food

case class Group(name: String, leader: Person, members: List[Person])

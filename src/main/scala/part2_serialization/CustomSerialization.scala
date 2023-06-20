package part2_serialization

import akka.serialization.Serializer

case class Person(name: String, age: Int)
class PersonSerializer extends Serializer {
  override def identifier: Int = 74238

  val SEPARATOR = "//"
  override def toBinary(o: AnyRef): Array[Byte] = o match {
    case person @ Person(name, age) =>
      // [John||32]
      println(s"Serializing $person")
      s"$name$SEPARATOR$age".getBytes()
    case _ => throw new IllegalArgumentException("only persons are supported for this serializer")
  }

  override def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    val string = new String(bytes)
    val values = string.substring(1, string.length - 1).split(SEPARATOR)
    val name = values(0)
    val age = values(1).toInt

    val person = Person(name, age)
    println(s"Deserialized $person")
    person
  }

  override def includeManifest: Boolean = false
}

class CustomSerialization {

}

# Akka Serialization with Scala
## Scala Recap

## Akka Recap


## Custom Serialization
Messages and events are serialized with Java by default
(serialize = turn objects into bytes to send over the wire)

Java Serialization sucks
- slow
- memory-heavy
- vulnerable

Write our own serializers
- custom format
- popular format: JSON

Serialization frameworks
- Avro
- Kryo
- Protobuf


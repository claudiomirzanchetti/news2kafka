# Hacker News To Apache Kafka

### Objective

This project demonstrates how to listen to and publish on Apache Kafka's topics using a Spring Boot Application. 

### How it works
Given a number of stories to publish, [Hacker News' API](https://github.com/HackerNews/API) is consumed to retrieve stories randomly, which are published on Kafka's output topic (hacker-news.output).

To inform the quantity of stories you can use the Kafka's input topic (hacker-news.input) using the kafka-console-producer CLI, as well the REST API: api/stories/{numberOfStoriesToPublish}.

Both topics are created automatically, so no additional configuration is needed.

### Useful links (Apache Kafka)

* Basic concepts: https://www.youtube.com/watch?v=U4y2R3v9tlY&ab_channel=Simplilearn
* Installation and configuration: https://www.youtube.com/watch?v=OJKesEpO6ok&t=369s&ab_channel=GopalTiwari

### Useful commands (on Windows - default installation parameters)
* To start Zookeeper (it needs to be in the systems' path): zkserver
* To start kafka: <kafka-base-dir>\bin\windows\kafka-server-start.bat .\config\server.properties
* To list the topics: <kafka-base-dir>\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181
* To inform the number of stories to publish: <kafka-base-dir>\bin\kafka-console-producer.bat --broker-list localhost:9092 --topic hacker-news.input
* To verify the stories published: <kafka-base-dir>\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic hacker-news.output

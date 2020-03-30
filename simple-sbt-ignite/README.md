## Simple SBT Ignite

SBT project demonstrates use of [apache-ignite](https://ignite.apache.org) for various use cases.

Apache Ignite is In memory computing platform. It provides
**Memory centric Storage | Distributed SQL | Distributed Key-Value | ACID Transactions | Collocated Processing** and also helps in train and deploying ML models. [Read more...](https://apacheignite.readme.io/docs)

### Project Structure
Project is divided in multiple modules, one *core* module and other feature modules depending upon *core*.

***core***
The *core* module consists of utils.

***distributedKVCache***
The *distributedKVCache* module demonstrates the use of Distributed KV caching for *Order*. It depends on *core* module.

### Run

##### **distributedKVCache**
To run *distributedKVCache* example, give command:
> sbt distributedKVCache/run

It should run the `CacheServer`, put a simple `Order` data into cache instance and the `CacheClient` to retrieve that `Order` by id.

```
[14:10:31] Ignite node started OK (id=be2a1dda, instance name=simple-ignite-instance)
[14:10:31] Topology snapshot [ver=1, locNode=be2a1dda, servers=1, clients=0, state=ACTIVE, CPUs=8, offheap=1.6GB, heap=1.0GB]
[14:10:31]   ^-- Baseline [id=0, size=1, online=1, offline=0]
...
Putting Order info: Order(323b68ec-1cb8-4110-c214-f75079b15719,IPhone 11 Pro,1,1699.0) into Cache!
...
[IgniteCacheClient] - Starting.
[IgniteCacheClient] - Initiating cache: order.
Order retrieved from Cache: Order(323b68ec-1cb8-4110-c214-f75079b15719,IPhone 11 Pro,1,1699.0)
```


#### **ignitePersistence** [TBD]
The *ignitePersistence* module demonstrates the use of ignite as 3rd party persistence. It uses `postgres` as database. It depends on *core* module.

Ensure, postgres is running on Docker. Configure postgres db in `application.conf`.

To run *ignitePersistence* example, give command:
> sbt ignitePersistence/run

It should run the `IgniteApp`.

#### **igniteKafkaStreamer**
The *igniteKafkaStreamer* module demonstrates use of ignite's KafkaStreamer. It's a bare minimum example.

> sbt igniteKafkaStreamer/run

Before giving above command, ensure Kafka is running:
> cd igniteKafkaStreamer
> docker-compose up

This should run kafka along with zookeeper(and schema-reg - not needed here).
> docker ps
Should give list of containers running.

Perform below to get into running container:
> docker exec -it broker /bin/bash

Go to below directory:
> cd usr/bin

Check list of topics:
> kafka-topics --list --zookeeper zookeeper:2181

Produce a sample message for streaming!
> kafka-console-producer --broker-list broker:9092 --topic simple.key.value.topic --property "parse.key=true" --property "key.separator=:"

Give a sample key-value
> key1:value1
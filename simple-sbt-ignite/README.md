## Simple SBT Ignite

SBT project demonstrates use of [apache-ignite](https://ignite.apache.org) for various use cases.

Apache Ignite is In memory computing platform. It provides
**Memory centric Storage | Distributed SQL | Distributed Key-Value | ACID Transactions | Collocated Processing** and also helps in train and deploying ML models. [Read more...](https://apacheignite.readme.io/docs)

### Project Structure
Project is divided in multiple modules, one *core* module and other feature modules depending upon *core*.

***core***
The *core* module consists of models e.g. *Order*, along with it's `CacheConfiguration`.

***distributedKVCache***
The *distributedKVCache* module demonstrates the use of Distributed KV caching for *Order*. It depends on *core* module.

### Run

**distributedKVCache**
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


**ignitePersistence**
Ensure, postgres is running on Docker. Configure postgres db in `application.conf`.

To run *ignitePersistence* example, give command:
> sbt ignitePersistence/run

It should run the `IgniteApp`, put a simple `Order` data into cache instance and the `CacheClient` to retrieve that `Order` by id.

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
## Simple Sbt Kafka [In Progress]

Project demonstrates use of Kafka in an scala project. It uses [Alpakka](https://doc.akka.io/docs/alpakka-kafka/current/home.html) library as a connector to Kafka server.
The project demonstrates use of AVRO as communication protocol for kafka hence a local AVRO schema registry,
along with Kafka on docker is used.

## Project Structure

Project is divided in 3 modules
- `kafka-support` provides bootstrap for Producer and Consumer classes.
- `order-api` lets you create an dummy `Order` which in turn, produces a `OrderCreated` event to `simple.order` topic.
- `order-processor` provides a Consumer interface, and subscribes to `simple.order` topic,
   which in turn receives `OrderCreated` event and does the complex job of printing it (Pun intended).

## Run
Project requires you to have [docker](https://www.docker.com/products/docker-desktop) installed and daemon running.
Why? - To setup Kafka on docker quickly.

Take a look at [docker-compose.yml](/docker-compose.yml) file, it defines 3 containers to run as part of kafka setup on docker.
This will use `docker-compose` to run kafka, zookeeper and schema-registry. The tool `docker-compose` comes with `Docker for Mac`
or Windows installation of docker.

To run kafka on docker, ensure you are in project directory
Open **Terminal/cmd prompt** :

From root directory, give below command to go in `simple-sbt-kafka` directory.
`cd simple-sbt-kafka`

And simply give the command
`docker-compose up`

It should run all three containers, you may ensure that in new terminal window, give command:
`docker ps`

The command should return, a list of 3 containers.

Once kafka is running, we'll run `orders-api`
`sbt ordersAPI/run`

Also, run `order-processor`
`sbt orderProcessor/run`

These commands should run ordersAPI and orderProcessor.

It should start the server at `localhost:8080`.

You may use [Postman](https://www.getpostman.com) to test APIs on this server.

In Postman, give a POST call to `localhost:8080/orders` with below request JSON:
```
{
	"id": "323b78ec-1eb8-4410-c284-f75079b15719",
	"title": "Apple IPhone 11 Pro",
	"price": 1699.0,
	"quantity": 1
}
```

When you click on send, it should return a response in success case.
```
{
    "message": {
        "id": "323b78ec-1eb8-4410-c284-f75079b15719",
        "price": 1699.0,
        "quantity": 1,
        "title": "Apple IPhone 11 Pro"
    },
    "success": true
}
```

Along with the response, it should also send a kafka message `OrderCreated` to topic `simple.order`, which the `order-processor` is listening.
On the terminal window where `orderProcessor` is running, you would be able to see the `OrderCreated` event printed.


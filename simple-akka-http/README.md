## Simple Akka Http

Project is uses [akka-http](https://doc.akka.io/docs/akka-http/current/introduction.html#using-akka-http).

**Two modules**
 1. core
 2. usage

Module `usage` depends on `core`.

- The `usage` module has `Server.scala` file, as the entry point.
- Within `Server`  we create an instance for `HttpService` with required `Config`. This configuration is taken from `resources/application.conf` as a call to `ConfigFactory.load()` method (see in `Server.scala`) and passed to `HttpService`.
- Within the `Server` object we also instantiate `ActorSystem` and `Materializer` as implicit, since these are required by `akka-http`.
- Within `HttpService`, we bind desired host and port from `application.conf` to server and run.
- Apart from `Config`, HttpService also takes `Route`, which is nothing but a *~~Hipster~~* term denoting:  **a way to convert http `Request` to `Response`**
- In object `HttpService`, we have [wired](https://github.com/softwaremill/macwire) `OrderRoutes` and passed.
- The class `OrderRoutes` requires `OrderService`, in other words `OrderService` dependency to be injected in `OrderRoutes`, hence that is also *wired*! This class provides routes for Order's API.
- `OrderService` contains slight dummy business logic and CRUD.

## Run
Open **Terminal/cmd prompt** :

From root directory, give below command to go in `simple-akka-http` directory.
`cd simple-akka-http`

To run the server, simply give the command
`sbt usage/run`

It should start the server at `localhost:8080`.

You may use [Postman](https://www.getpostman.com) to test APIs on this server. `OrderRoutes` lets you create an order through a POST call to `/orders`

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

Note: Didn't try to explain every detail, to understand `akka-http` refer to it's documentation.
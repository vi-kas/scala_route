## Akka Http Auth
Provides Authentication Directive for `akka-http` routes.

### Modules structure
**jwt_lib**
Contains code for `JWT` generation and validation.

**akka_auth**
Contains `akka-http` directives for JWT based authentication (and authorization TBD). It uses `jwt_lib` for JWT generation/validation.

**akka_http_auth_app**
Demonstrates how to use auth directives from `akka_auth` module. It has a test case demonstrating the use of `akka_auth`

### Running test case
- Ensure current directory is `akka_http_auth`
- Give `sbt akka_http_auth_app/test`

Above command should run the test case `AkkaHttpRoutesSpec`.
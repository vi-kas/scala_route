## Simple Scala Project

With an Object SimpleMaths in `usage` module. It has a functionality to check if a number is `Even` or not.

**Two modules**
 1. core
 2. usage
 
Module `usage` depends on `core`.

## Library Dependencies

 **Scalatest**
```
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",  
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
```

## Run
To run, simply give the command
> sbt usage/run

To Check if a Number is Even or Odd give command
> sbt "usage/run 2"

In above code snippet we are checking if 2 is Even/odd. (So hard to guess ;) 

## Test
Test cases can be found in `SimpleMathsSpec`. To test, simply give the command
> sbt usage/test

Like shown here:
```
~/Workspace/scala_route (👉) $ sbt usage/run
[info] Loading project definition from ~/Workspace/scala_route/project
[info] Loading settings for project root from build.sbt ...
[info] Set current project to scala_route
[info] Running HelloWorld
This is a Simple Scala Project.
[success] Total time: 1 s
```

## Git Branch Info
Branch name - `scala-jenkins-docker`

Branch from - `master`

## What's in it?
Set up simple Jenkins Build for SBT on Docker.

## Docker
Installed Docker for Mac
```dockerfile
> docker --version
Docker version 19.03.1, build 74b1e89
```

## Jenkins on Docker
The idea was to use `jenkins`'s docker image and load `sbt` onto that.
```dockerfile
FROM jenkins/jenkins:lts

ENV SBT_VERSION 1.2.8

USER root

RUN wget https://repo1.maven.org/maven2/org/scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch.jar && \
    mv sbt-launch.jar /bin && \
    cd /bin && \
    ls

USER jenkins
```
1. Refers to [jenkins/jenkins:lts](https://hub.docker.com/r/jenkins/jenkins/) docker image.
2. Defined environment variables e.g. SBT_VERSION
3. Changes container's user to `root` 
4. Run's a command which fetches `sbt-launcher` and moves it to `/bin` folder.
5. Finally changes the user back to `jenkins`

### Building `docker` image
Once the `Dockerfile` is ready, build it.
```shell script
docker build -t jenkins_sbt .
```

It will create/pull two docker images.
```shell script
> docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
jenkins_sbt         latest              cca4efebe247        3 hours ago         572MB
jenkins/jenkins     lts                 f32b4bb22e4d        3 days ago          571MB
```

### Running `jenkins` on `docker`
Once the `jenkins_sbt` image is ready, run it.
```shell script
docker run -d -v ~/jenkins_home:/var/jenkins_home --name=jenkins_yo -p 8080:8080 -p 50000:50000 jenkins_sbt
```
With above command:
 
Docker runs the `jenkins_sbt` image, mounts volume `~/jenkins_home`, binds host machine port `8080` to container port `8080`, runs in daemon mode `-d` and assigns a name `jenkins_yo`

### Setting up `jenkins` on `docker` for the first time - `Without Jenkinsfile`
* Open chrome, go to `http://localhost:8080/`
* Follow this [tutorial](https://medium.com/@gustavo.guss/quick-tutorial-of-jenkins-b99d5f5889f2) to setup account and login 

References: 
1. [Jenkins on Docker Reference](https://github.com/jenkinsci/docker/blob/master/README.md)
2. [Dockerfile Reference](https://docs.docker.com/engine/reference/builder/)

### Jenkins sbt Plugin
So now - You have `jenkins` running on `localhost:8080`, a user logged in and default plugins installed.
* Go to Manage Plugins -> Available tab -> search for sbt -> install without restart.
* Go back to Dashboard -> Manage Jenkins -> Global Tool Configuration -> Sbt
* Add sbt -> Give it any name
* In sbt-launch jar -> Give `/bin/sbt-launch.jar` -> Apply/Save -> Go to Dashboard

### Write a simple `Build`
* From Jenkins Dashboard -> New Item -> Give any Name
* For Source Code Management -> Add git URL
* In Build Section -> Add `Build using sbt`, it will use the sbt-launcher we defined.
* In this in `actions` field give -> `compile test`, for them to be performed -> Apply/Save
* Click on the project from Dashboard, and click `Build Now`.



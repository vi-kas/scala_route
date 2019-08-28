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
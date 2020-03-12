## Simple Scala Project

Project is a simple project with 2 modules, it uses [sbt](https://www.scala-sbt.org/1.x/docs/sbt-by-example.html) as build tool.
  
**Two modules**  
 1. core  
 2. usage  

There's an object `SimpleMaths` in `usage` module, it has a functionality to check if a number is `Even` or not.
Module `usage` depends on `core`.
  
## Library Dependencies  
  
 **Scalatest**  
```
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",    
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"  
```  
  
## Run  
Open **Terminal/cmd prompt** :  
  
From root directory, give below command to go in `simple-scala-sbt` directory.  
`cd simple-scala-sbt`
  
To run, simply give the command  
`sbt usage/run`
  
To Check if a Number is Even or Odd give command  
`sbt "usage/run 2" ` 
  
In above code snippet we are checking if 2 is Even/odd. (So hard to guess, right? ;)   
  

## Test  
Test cases can be found in `SimpleMathsSpec`. To test, simply give the command  
`sbt usage/test` 
  
As shown here:
```  
~/Workspace/scala_route (👉) $ sbt usage/run  
[info] Loading project definition from ~/Workspace/scala_route/project  
[info] Loading settings for project root from build.sbt ...  
[info] Set current project to scala_route  
[info] Running HelloWorld  
This is a Simple Scala Project.  
[success] Total time: 1 s  
```
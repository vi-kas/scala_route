## Simple Akka Http K8s

SBT project demonstrates how we can create a simple RESTFul HTTP service, dockerize it and create a Kubernetes pod for deployment.
(Means creating CI/CD using Jenkins and running on kubernetes)

In the end, it we'll have a running [Kubernetes](https://kubernetes.io)(k8s) [pod](https://kubernetes.io/docs/concepts/workloads/pods/pod-overview/), which underneath will be running [OrderService](https://github.com/vi-kas/scala_route/tree/master/simple-akka-http).

You'll be able to send a request to `localhost:8081/orders` with a JSON request:
```json
{
	"id": "323b78ec-1eb8-4410-c284-f75079b15719",
	"title": "Apple IPhone 11 Pro",
	"price": 1699.0,
	"quantity": 1
}
```

And get a success response.

We'll take a step by step to move forward:
 - Running *orderService* locally
 - Setting up *Jenkins* on Docker
 - Writing a `Dockerfile` for the application
 - Writing `Jenkinsfile` for jenkins deployment
 - Creating a *Jenkins* Pipeline Job
 - Running a *Jenkins* Pipeline job
 - Running *Kubernetes* using **Docker for Desktop**

### Running `orderService` locally

We have an SBT project, hence a `build.sbt` is defined at the project root. It has a module named `orderService`, which is essentially an [akka-http](https://doc.akka.io/docs/akka-http/current/introduction.html#using-akka-http) service.

To run the project locally, open cmd prompt/terminal.
Ensure you're in the right directory, i.e. `simple-akka-http-k8s`. From here give:
> sbt orderService/run

Above command should run the service on PORT 8080, you may use [POSTMAN](https://www.postman.com/downloads/) to interact with the service.

In Postman, give a POST call to  `localhost:8080/orders`  with below request JSON:

```json
{
	"id": "323b78ec-1eb8-4410-c284-f75079b15719",
	"title": "Apple IPhone 11 Pro",
	"price": 1699.0,
	"quantity": 1
}
```

When you click on send, it should return a response in success case.
```json
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

If you get such a response, great! Next, we would set up CI/CD using Jenkins

 Let's move ahead to setup [Jenkins](https://jenkins.io)

### Setting up Jenkins on Docker

Download and Install [Docker](https://www.docker.com/products/docker-desktop), once installed, ensure docker daemon is running!

Before running, we'll create a docker volume to store Jenkins data.
> cd ~
> mkdir jenkins_home

Next, run the `jenkins`  docker image with some parameters:
```bash
docker run -u root --name jenkins_d -p 8081:8080 -p 50000:50000 -v ~/jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkins/jenkins:lts
```

Above command runs `jenkins`  image as a root user, using two volumes, one for jenkins data and another so that `docker` can use host's docker daemon. The command also includes port binding arguments, host machine's port `8081` is bound to container's `8080` (where jenkins will run).

Once we run the command, docker should run `Jenkins`, and in the set of logs it throws on your terminal, you might find `initialAdminPassword`. We'll copy that, it will be used when we first open `Jenkins`.

Open web browser, and go to `http://localhost:8081`. It should take you through `Jenkins` setup, use the `initialAdminPassword`, complete the setup by installing default plugins and creating a new user!

Once the setup is complete, login using the user credentials!

**Install SBT Plugin**
[TBD]
> Jenkins --> Configure Jenkins --> Manage Plugins --> Available --> SBT

Choose SBT plugin and install without restart, we'll configure installations later.

**Install Docker Plugin**
[images TBD]
> Jenkins --> Configure Jenkins --> Manage Plugins --> Available --> Docker

Choose Docker plugin and install without restart, we'll configure installations later.

**Create Docker Global Credentials**
[images TBD]
> Jenkins --> Credentials --> System --> Global Credentials --> Add Credentials

Add your docker credentials here e.g. `dockerhub`.

**Configuring Installations**
[images TBD]
> Jenkins --> Global Tool Configurations --> SBT Installations

Give a name, e.g. `jen_sbt` etc.
Choose install from website.

Perform similar steps for docker, by creating a docker installation e.g. `jen_docker`.

With this, jenkins is pretty much up and running. It's time to push the code to GitHub and configure a pipeline job.

### Writing a `Dockerfile` for the app

Idea is to `build` and `push` docker image as part of CI/CD pipeline on every push to GitHub branch. But we don't want to create a `Dockerfile` again and again, hence it's ok to create one in GitHub repository.

```docker
FROM openjdk:8-jre
ADD orderService/target/orderService.jar /app/orderService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "\/app\/orderService.jar"]
```

This is how it looks like, you may find it in the root of project. It uses `openjdk` image and builds on top of that. Next, steps include creating an `assembly` jar file for the application, which is a fat-jar file containing source, libraries etc. and running that.

**Creating orderService application assembly jar file**
For creating an assembly jar file, the project is using an sbt plugin [sbt-assembly](https://github.com/sbt/sbt-assembly). This is defined in `project/plugins.sbt` file.
We specified the location to put jar file and also the entry `mainClass` in `build.sbt` file.
```
assemblyOutputPath in assembly := baseDirectory.value / "target" / "orderService.jar" ,
mainClass in assembly := Some("io.github.vi_kas.Server")
```

Giving a command:
> assembly

Should create the `orderService.jar` at base directory's target folder as configured.


### Writing a `Jenkinsfile` for app deployment

The project root directory also includes a `Jenkinsfile`, let's take a look at it's content:

```json
pipeline {
    agent any

    environment {
        dockerImage = ''
        registry = 'vikasharma02/vik_repo'
        registryCredential = 'dockerhub'
    }

    stages {
        stage('Build') {
            steps {
               echo "Compiling..."

               sh "cd simple-akka-http-k8s && ${tool name: 'jen_sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt orderServiceCompile"
            }
        }
        stage('Unit Test') {
            steps {
               echo "Testing..."

               sh "cd simple-akka-http-k8s && ${tool name: 'jen_sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt orderServiceTest"
            }
        }
        stage('Assembly') {
            steps {
               echo "Jar in making..."

               sh "cd simple-akka-http-k8s && ${tool name: 'jen_sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt assembly"
            }
        }
        stage('Docker Build') {
            steps {
               echo "Building Docker image..."

               script {
                   docker.withTool('jen_docker') {
                       dockerImage = docker.build(registry + ":latest", 'simple-akka-http-k8s/')
                   }
               }
            }
        }
        stage('Docker Push') {
            steps {
                echo "Pushing Docker Image..."

                script {
                    docker.withTool('jen_docker') {
                        docker.withRegistry('', registryCredential ) {
                            dockerImage.push()
                        }
                    }
                }
            }
        }
    }
}
```

Here, [Pipeline](https://jenkins.io/doc/book/pipeline/) includes 5 stages:

- Build
- Unit Test
- Assembly
- Docker Build
- Docker Push

The `Build` and `Unit Test` stages use `sbt`'s command aliases which we defined in `build.sbt`.
```
addCommandAlias("orderServiceCompile", "orderService/compile")
addCommandAlias("orderServiceTest", "orderService/test")
```

It also declares few environment variables:
 `dockerImage` - a variable referencing to built docker image
 `registry`  - docker registry, the image is supposed to push into.
 `registryCredential` - docker login credentials to get access to docker registry.

[TBD] - Explaination for Pipeline

Note: The project `simple-akka-http-k8s` is a subproject from a set of projects for the [repo](https://github.com/vi-kas/scala_route), hence we have to `cd simple-akka-http-k8s &&` before few pipeline scripts. Ensure, if you need this kind of setup. If not, then remove `cd simple-akka-http-k8s` from pipeline scripts.

### Creating a *Jenkins* Pipeline job

[image TBD]
Ensure you're logged in to Jenkins (http://localhost:8081)
> Jenkins --> New Item --> Give Name --> Select Pipeline --> Next

Next,
> Pipeline Tab --> Give Name/Description --> From simple-akka-http-k8s/Jenkinsfile --> OK

Also configure the git repo and branch, you're pushing the code to!

### Running a *Jenkins* Pipeline job

Once you have configured the Pipeline Job, push the code to GitHub repo and switch to `Jenkins` on browser and click on `Build Now`. It should run the Pipeline.

If everything is configured properly, the build should be successful (Hopefully!).

### Running *Kubernetes* using Docker for Desktop

[\[Windows\] - Here](https://collabnix.com/kubernetes-dashboard-on-docker-desktop-for-windows-2-0-0-3-in-2-minutes/)

On you Mac, get set, GO!!
>Click on *Docker* icon from Top Right Corner
> Go to *Preferences*, click on *Kuberneter* tab
> Click the enable checkbox

It'll take some time, but should run in a couple of minutes.

### Creating a *Kubernetes* pod for the app

The project root directory has a `kubernetes` folder, you may find a [deployment](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) yaml file `simple-akka-http-k8s.yaml` with content like this:

```yaml
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
 name: simple-akka-http
  labels:
	 app: simple-akka-http
spec:
 replicas: 1
 selector:
	 matchLabels:
		 app: simple-akka-http
  template:
	 metadata:
	   labels:
		  app: simple-akka-http
     spec:
		 containers:
		   - image: vikasharma02/vik_repo:latest
		     imagePullPolicy: IfNotPresent
		     name: simple-akka-http
		     env:
			 - name: ENV_VAR_1
			   value: ENV_VAR_VALUE_1
```

That's how the deployment yaml file looks for the application. Note the `containers` specifies the exact docker image we pushed.

For creating a `pod`, we should create the `deployment` first. Hence, go to the kubernetes folder and give:

> kubectl apply -f simple-akka-http-k8s.yaml

It should successfully create a deployment for you (if no yaml validation errors :D ).

You may check the list of deployments.
> kubectl get deployments

Once, the deployment is done, you may see the pod is going to be spin up for you.
> kubectl get pods

It might take some time, not much though until you see the `status` as `Running`.

Great!

### Trying out orderService through *Kubernetes* pod

For trying out, we'll simply create a `port-forwarding` from Kubernetes pod to host machine's port.
> kubectl port-forward simple-akka-http-aweawesss 8082:8080

This will forward all request to pod's `8080` Port to host machine's `8082` port.

Once, that's done, open POSTMAN and give a POST call to  `localhost:8082/orders`  with below request JSON:

```json
{
	"id": "323b78ec-1eb8-4410-c284-f75079b15719",
	"title": "Apple IPhone 11 Pro",
	"price": 1699.0,
	"quantity": 1
}
```

When you click on send, it should return a response in success case.
```json
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

You saw the difference in `PORT`? , it's `8082` this time.

And you've successfully ran the service, along with the CI/CD pipeline.
Congratulations!
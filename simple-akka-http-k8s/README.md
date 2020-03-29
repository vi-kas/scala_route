## Simple Akka Http K8s

jenkins_u/jenkinsuser

```
docker run -d -u root --name jenkins_d -p 8081:8080 -p 50000:50000 -v ~/jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkins/jenkins:lts
```
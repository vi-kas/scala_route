FROM jenkins/jenkins:lts

MAINTAINER Vikash Sharma (vika.sharma@live.com)

ENV SBT_VERSION 1.2.8
ENV SBT_HOME /opt/sbt
ENV PATH $PATH:/opt/sbt/bin/

# Just before executing and moving folders around (see below in RUN), we are switching the user to root
USER root

RUN mkdir -p /opt/sbt/bin

# We neet sbt-launcher for configuring in Jenkins-SBT-Plugin
# So we download sbt-launcher for desired SBT_VERSION and move it to /bin folder
# Last two commands are just to check things are cool!
RUN wget https://repo1.maven.org/maven2/org/scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch.jar && \
    mv sbt-launch.jar /opt/sbt/bin && \
    ls

WORKDIR /opt/sbt/bin

RUN ls

WORKDIR /

RUN apt-get update && \
    apt-get -y install apt-transport-https \
    ca-certificates \
    curl \
    gnupg2 \
    software-properties-common && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
    $(lsb_release -cs) \
    stable" && \
    apt-get update && \
    apt-get -y install docker-ce

RUN apt-get install -y docker-ce

EXPOSE 8080

# Once execution is done, switching back to jenkins user
USER jenkins
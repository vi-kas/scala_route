FROM jenkins/jenkins:lts

MAINTAINER Vikash Sharma (vika.sharma@live.com)

ENV SBT_VERSION 1.2.8

# Just before executing and moving folders around (see below in RUN), we are switching the user to root
USER root

# We neet sbt-launcher for configuring in Jenkins-SBT-Plugin
# So we download sbt-launcher for desired SBT_VERSION and move it to /bin folder
# Last two commands are just to check things are cool!
RUN wget https://repo1.maven.org/maven2/org/scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch.jar && \
    mv sbt-launch.jar /bin && \
    cd /bin && \
    ls

# Once execution is done, switching back to jenkins user
USER jenkins
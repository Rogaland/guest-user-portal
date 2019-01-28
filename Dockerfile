FROM gradle:2.13-jdk8-alpine as builder
USER root
COPY . .
RUN gradle --no-daemon build
RUN find . -ls

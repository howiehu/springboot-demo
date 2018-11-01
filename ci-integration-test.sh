#!/bin/sh

docker run --rm -v "$PWD":/home/gradle/springboot-demo -w /home/gradle/springboot-demo gradle:4.10.2-jdk11 gradle --no-daemon -s test --tests name.huhao.springbootdemo.integration.*

#!/bin/sh

docker run --rm -v "$PWD":/home/gradle -w /home/gradle gradle:4.10.2-jdk11 gradle --no-daemon -s test --tests name.huhao.springbootdemo.integration.*

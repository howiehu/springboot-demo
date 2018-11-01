#!/bin/sh

docker run --rm -v "$PWD":/springboot-demo -w /springboot-demo gradle:4.10.2-jdk11 gradle -s test --tests name.huhao.springbootdemo.e2e.*

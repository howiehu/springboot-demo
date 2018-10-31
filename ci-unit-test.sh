#!/bin/sh

docker run --rm -v "$PWD":/app -w /app gradle:4.10.2-jdk11 gradle test --tests name.huhao.springbootdemo.unit.*

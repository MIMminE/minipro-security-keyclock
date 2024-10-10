#!/bin/bash

docker build -t my-keyclock .

docker run -d --name keyclock -p 8888:8080 my-keyclock

echo "docker run success!"
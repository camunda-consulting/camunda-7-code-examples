#!/bin/sh -e

jar uf /app.jar /*.bpmn
exec java -jar /app.jar
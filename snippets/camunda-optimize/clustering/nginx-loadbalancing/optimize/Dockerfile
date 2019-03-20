FROM openjdk:8-jre-slim

ENV TOKEN_SCRET {{ TOKEN_SCRET }}
ENV IMPORT_ENABLED {{ IMPORT_ENABLED }}

EXPOSE 8090 8091
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

ADD ./camunda-optimize.zip /camunda-optimize.zip
RUN unzip -d /opt/camunda-optimize camunda-optimize.zip

# Add license
ADD ./OptimizeLicense.txt /opt/camunda-optimize/environment/OptimizeLicense.txt

RUN groupadd camunda && useradd -ms /bin/bash -G camunda optimize && chown -R root:camunda /opt/camunda-optimize && chmod -R g+w /opt/camunda-optimize
USER optimize:camunda

ENTRYPOINT [ "sh", "-c", "/opt/camunda-optimize/optimize-startup.sh" ]

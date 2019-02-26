FROM openjdk:8-jre-slim

EXPOSE 8090 8091
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

ADD ./camunda-optimize.zip /camunda-optimize.zip
RUN unzip -d /opt/camunda-optimize camunda-optimize.zip

# Optimize Config with SSO plugin
ADD ./environment-config.yaml /opt/camunda-optimize/environment/environment-config.yaml

# Add license
ADD ./OptimizeLicense.txt /opt/camunda-optimize/environment/OptimizeLicense.txt

# SSO plugin
ADD ./sso-keycloak-example-plugin/target/sso-keycloak-example-plugin-0.0.1-SNAPSHOT.jar /opt/camunda-optimize/plugin/sso-keycloak-example-plugin.jar

RUN groupadd camunda && useradd -ms /bin/bash -G camunda optimize && chown -R root:camunda /opt/camunda-optimize && chmod -R g+w /opt/camunda-optimize
USER optimize:camunda

ENTRYPOINT [ "sh", "-c", "/opt/camunda-optimize/optimize-startup.sh" ]

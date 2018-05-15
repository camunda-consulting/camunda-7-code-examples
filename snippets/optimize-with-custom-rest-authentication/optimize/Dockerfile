FROM openjdk:8 as unpackingAsUser
RUN useradd -ms /bin/bash -d /camunda camunda
USER camunda
WORKDIR /camunda
COPY camunda-optimize-2.0.0-full.tar.gz /optimize.tar.gz
RUN tar xzf /optimize.tar.gz

FROM openjdk:8
RUN useradd -ms /bin/bash -d /camunda camunda
USER camunda
WORKDIR /camunda
COPY --from=unpackingAsUser --chown=camunda:camunda /camunda ./
CMD ./start-optimize.sh
EXPOSE 8090
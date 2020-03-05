FROM openjdk:8-jdk-alpine

# Create app directory
WORKDIR /usr/src/app

#TODO: run maven build in docker image
#RUN mvn clean install -DskipTests

COPY target/dynamic-tenants.jar ${WORKDIR}

ENTRYPOINT ["java","-Dserver.port=8080","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/src/app/dynamic-tenants.jar"]

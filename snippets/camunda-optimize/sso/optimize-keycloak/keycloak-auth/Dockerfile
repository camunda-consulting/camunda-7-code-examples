FROM jboss/keycloak:4.3.0.Final

ENV KEYCLOAK_USER admin
ENV KEYCLOAK_PASSWORD admin

ADD camunda-demo-realm.json /

CMD ["-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-Dkeycloak.import=/camunda-demo-realm.json"]

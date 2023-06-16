package com.camunda.consulting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdditionalUserCreator {
  private static final Logger LOG = LoggerFactory.getLogger(AdditionalUserCreator.class);
  private static final AdditionalUserCreator INSTANCE = new AdditionalUserCreator();

  private AdditionalUserCreator() {}

  public static AdditionalUserCreator getInstance() {
    return INSTANCE;
  }

  public void createUsers(Properties properties) {
    Map<EntityType, Map<String, List<EntityAttribute>>> entities =
        properties.stringPropertyNames().stream()
            .map(p -> Map.entry(p, properties.getProperty(p)))
            .map(e -> Map.entry(e.getKey().split("\\."), e.getValue()))
            .map(e -> processUrn(e.getKey(), e.getValue()))
            .filter(Objects::nonNull)
            .collect(
                Collectors.groupingBy(e -> e.entityType, Collectors.groupingBy(e -> e.entityId)));
    LOG.info("Entities created");
    // create users
    entities
        .getOrDefault(EntityType.USER, Collections.emptyMap())
        .forEach(
            (userId, attributeList) ->
                StaticIdentityProvider.getInstance()
                    .createUser(
                        userId,
                        getAttributeValue(attributeList, "password"),
                        getAttributeValue(attributeList, "firstName"),
                        getAttributeValue(attributeList, "lastName"),
                        getAttributeValue(attributeList, "email")));
    LOG.info("Users created");
    // create groups
    entities
        .getOrDefault(EntityType.GROUP, Collections.emptyMap())
        .forEach(
            (groupId, attributeList) ->
                StaticIdentityProvider.getInstance()
                    .createGroup(
                        groupId,
                        getAttributeValue(attributeList, "name"),
                        getAttributeValue(attributeList, "type")));
    LOG.info("Groups created");
    // create assignments
    entities
        .getOrDefault(EntityType.ASSIGNMENT, Collections.emptyMap())
        .forEach(
            (id, attributeList) ->
                attributeList.forEach(
                    attribute -> {
                      String userId = attribute.attributeName;
                      Arrays.stream(attribute.attributeValue.split(","))
                          .map(String::trim)
                          .forEach(
                              groupId ->
                                  StaticIdentityProvider.getInstance()
                                      .createAssignment(userId, groupId));
                    }));
    LOG.info("Assignments created");
  }

  private String getAttributeValue(List<EntityAttribute> attributeList, String attributeName) {
    return attributeList.stream()
        .filter(attribute -> attribute.attributeName.equals(attributeName))
        .map(attribute -> attribute.attributeValue)
        .findFirst()
        .orElse(null);
  }

  private EntityAttribute processUrn(String[] urn, String value) {
    EntityAttribute attribute = new EntityAttribute();
    attribute.attributeValue = value;
    // check urn is valid: first block -> length -> attributeName
    if (urn != null && urn.length >= 2) {
      attribute.entityId = urn[1];
      if ("user".equals(urn[0])) {
        attribute.entityType = EntityType.USER;
        if (urn.length == 3) {
          attribute.attributeName = urn[2];
          if (List.of("firstName", "lastName", "email", "password")
              .contains(attribute.attributeName)) {
            return attribute;
          }
        }
      }
      if ("group".equals(urn[0])) {
        attribute.entityType = EntityType.GROUP;
        if (urn.length == 3) {
          attribute.attributeName = urn[2];
          if (List.of("name", "type").contains(attribute.attributeName)) {
            return attribute;
          }
        }
      }
      if ("assignment".equals(urn[0])) {
        attribute.entityId = UUID.randomUUID().toString();
        attribute.entityType = EntityType.ASSIGNMENT;
        if (urn.length == 2) {
          attribute.attributeName = urn[1];
          return attribute;
        }
      }
    }
    LOG.info("Ignoring invalid property {}", String.join(".", urn));
    return null;
  }

  private enum EntityType {
    USER,
    GROUP,
    ASSIGNMENT
  }

  private static class EntityAttribute {
    private EntityType entityType;
    // not required for assignments
    private String entityId;
    private String attributeName;
    private String attributeValue;
  }
}

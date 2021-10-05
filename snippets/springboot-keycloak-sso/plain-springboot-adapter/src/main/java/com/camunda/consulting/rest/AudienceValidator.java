package com.camunda.consulting.rest;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Token validator for audience claims.
 */
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  /** The required audience. */
  private final String audience;

  /**
   * Creates a new audience validator
   * 
   * @param audience
   *          the required audience
   */
  public AudienceValidator(String audience) {
    this.audience = audience;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    if (jwt.getAudience().contains(audience)) {
      return OAuth2TokenValidatorResult.success();
    }
    return OAuth2TokenValidatorResult
        .failure(new OAuth2Error("invalid_token", "The required audience is missing", null));
  }
}
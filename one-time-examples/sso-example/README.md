# Enable SSO in your Spring Boot Camunda 7 Engine

## Add Spring Security

``` 
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
 </dependency>
```

When starting the application you will now see the following login screen:

![Spring Security enabled](documentation%2F01-spring-security-enabled.png)

You will find the following statement in the logs:

``` 
Using generated security password: 522aa624-e7cd-4fe4-a6de-b0d2c21de362

This generated password is for development use only. Your security configuration must be updated before running your application in production.
``` 

If you now login with username: user and the auto-generated password, you will be redirected to Camunda. 
However, you can see that you need to login again (default: demo/demo).

So currently we have:
1. ✔️ A custom login procedure
2. ❌ No SSO Integration
3. ❌ No mapping to Camunda

## Configure SSO
Next you have to add the oauth client dependency.

```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
```
Client can be configured via the application.yaml

```
spring:
  security:
    oauth2:
      client:
        registration:
          custom:
            client-id: myclient
            client-secret: mysecret
            scope: openid, profile, email
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/custom
        provider:
          custom:
            issuer-uri: https://example.com/oauth2/default
```
Also you now have to add the SecurityConfig

```
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/camunda/app/**").authenticated() // All requests require authentication
        )
        .oauth2Login(oauth2Login ->
            oauth2Login
                .defaultSuccessUrl("/", true)
        )
        .csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }
}
```

Now on startup, you will be redirected to your configured identity provider.

![02-oauth-login-enabled.png](documentation%2F02-oauth-login-enabled.png)

If you now login with your credentials, you will be redirected to Camunda.
However, you can see that you need to login again (default: demo/demo).

So currently we have:
1. ✔️ A custom login procedure
2. ✔️ SSO Integration
3. ❌ No mapping to Camunda

## Map to Camunda

Now we add a ContainerBasedAuthenticationFilter
```
 @Bean
  public FilterRegistrationBean containerBasedAuthenticationFilter(){

    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
    filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", CustomAuthenticationProvider.class.getCanonicalName()));
    filterRegistration.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
    filterRegistration.addUrlPatterns("/camunda/app/*");
    return filterRegistration;
  }
```

In our CustomAuthenticationProvider we can now extract the Information from the JWT.

```
public class CustomAuthenticationProvider extends ContainerBasedAuthenticationProvider {

private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

@Override
public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    if (authentication == null) {
      return AuthenticationResult.unsuccessful();
    }

    OidcUser user = (OidcUser) authentication.getPrincipal();
    String name = user.getGivenName();
    //name = "demo";
    List<String> groups = user.getClaimAsStringList("groups");

    LOG.debug("extracted user: {}", name);
    if (name == null || name.isEmpty()) {
      return AuthenticationResult.unsuccessful();
    }

    AuthenticationResult authenticationResult = new AuthenticationResult(name, true);
    authenticationResult.setGroups(groups);

    return authenticationResult;
}

}
```


So now we have:
1. ✔️ A custom login procedure
2. ✔️ SSO Integration
3. ✔️ Mapping to Camunda
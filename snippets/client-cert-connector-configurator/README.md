# Client Certificate Authentication with HTTP-Connector
## Basics
### Client Certificates
- Client certificates are usually stored in a keystore.
  - A keystore is a file on your disk containing several public and private keys that are used to identify your self in front of a secured system.
  - Here we use a `.p12` file (other valid suffices would be `.pfx`, `.pkcs12`)
  - This keystore file and the contained keys are encrypted and readable using the same password `badssl.com`
- The class `CertHttpClientConfigurator` shows how to read the file and attach the keystore to an implementation of our `AbstractHttpConnector`
### Camunda's Configurator Pattern
The docs explain how to overwrite the behavior of the predefined connectors, in our case the HTTP Connector:

- https://docs.camunda.org/manual/latest/reference/connect/http-connector/#custom-configuration

The implementation in `CertHttpClientConfigurator` is inspired by that example.

### SPI - Service Provider Interface
- From my basic understanding, the implementation of the Configurator `CertHttpClientConfigurator` has to be introduced to the engine.
- For this part of Camunda the pattern SPI is being used.
- To do so you need to create the file `org.camunda.connect.spi.ConnectorConfigurator` in
    ``` 
    /src/main/resources/META-INF/services
    ```
- The file contains the fully qualified name of the implementation.

## How to build
```
mvn clean package
```

- Then copy the jar in target to the plugins folder of your app server.
- Restart Camunda
- Check for the string `http client configured` in the outprint. If it is there then the connector is active

## How to use
- use the http-connector functionality within the modeler
- the engine will use the updated client cert http connector.
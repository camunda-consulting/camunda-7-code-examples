# User Task Data Cache

This project shows an example how you can build a user task data cache. The Spring Boot version uses Elasticsearch to index the user tasks.

## Run the example

1. Run Elasticsearch:

    ```bash
    docker run --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.17.6
    ```

2. Run the app:
   
   ```bash
   mvn clean install
   mvn spring-boot:run -f spring-boot-example/pom.xml
   ```
   
This should start the spring boot app, connect it to the elasticsearch instance running in docker and create indexes for the created user tasks.

To verify, you can query elasticsearch:

```bash
curl http://localhost:9200/task-data/_search
```
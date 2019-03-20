# Camunda Optimize Load Balancer Example nginx

This example demonstrates how you can run multiple instances of the Optimize Backend in parallel and use a load balancer like nginx to route the requests dynamically.

The most important part from the Optimize perspective of this example are the different environment-config.yaml files:
 - [one for the Optimize instance that is importing data from our engine](./optimize/environment-config-importer.yaml)
 - [one for the Optimize instances that are not importing data but just serving requests](./optimize/environment-config.yaml)


To find out more about the Optimize Clustering mechanism, find the docs [here](https://docs.camunda.org/optimize/latest/technical-guide/setup/clustering/)

It includes a docker-compose with:
1. Camunda Optimize: 3 instances (1 importing, 2 not importing but serving requests)
2. ElasticSearch
3. Camunda BPM
4. Load Balancer (nginx)

# How to run?

## Clone the Repo

## Download and Configure Optimize

1. Download [Camunda Optimize](https://camunda.org/enterprise-release/optimize/2.4.0/camunda-optimize-2.4.0-production.zip) to folder `./optimize/`
2. Rename to `camunda-optimize.zip`
3. Place Optimize license in OptimizeLicense.txt

## Run docker-compose

1. Build all images with `docker-compose build`
1. Login to private Camunda Docker EE Registry with `docker login registry.camunda.cloud` Use your EE LDAP credentials to log in.
1. Start all images `docker-compose up -d`

## Open Optimize

1. Open WebBrowser
2. Open `localhost:8080`
3. Login with: `demo:demo`

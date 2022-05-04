# Cluster4
## The Fourth Cluster: Anggel, Miguel, Aisha


## Chip-Shop API: P2
> For our Project 2, we utilized the Chip-Shop API. The Chip-Shop API is a RESTful API that allows you to access and manipulate the data in the Chip-Shop database depending on your authorization level. We then configured docker to maven package and upload the new docker image to our dockerhub repo, as well as configured Kubernetes to deploy the chip-shop image, grafana-loki, prometheus, ingress controller, as well as the postgres database. We were then able to build our Jenkins pipeline utilizing SonarCloud, Aws, Docker, and Kubernetes plugins. This allowed us to get the access neccessary to each indiviual element through our Jenkins pipeline and deploy to our Kubernetes cluster. After the deployment, we were able to access our exposed logs and metrics through the grafana dashboard, which allowed us to setup our SLI's to monitor our application.   

## Technologies Used

Technologies

* Java
* Maven
* Spring
* JUnit
* Mockito
* JWT
* Docker
* Promtail
* Loki
* Grafana
* Prometheus
* Kubernetes
* Jenkins

### How to use Chip-Shop API
>
    * Post: auth/login
        * for user login
    * Post: auth/register
        * for user to register a new account
        * registers users are only customer accounts
    * GET: /brands
        * retrieve all brands
    * GET: /flavors
        * retrieve all flavors
    * GET: /brands/1/flavors
        * retrieve all flavors of that brand

## Contributors

Miguel Garcia, Anggel Plasencia, Ai'sha Gaffney



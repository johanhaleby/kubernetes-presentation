# kubernetes-presentation
Some example used during my Kubernetes presentation

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server
    
## Dockerify

    lein uberimage

## Running in Docker
    
    docker run --rm -p 3000:3000 gcr.io/parkster-env-stughelg/kubernetes-presentation:1.0.0

## Pushing to GKE Registry

    gcloud docker push gcr.io/parkster-env-stughelg/kubernetes-presentation:1.0.0

## License

Copyright © 2016 FIXME
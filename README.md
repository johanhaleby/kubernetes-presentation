# kubernetes-presentation
Some example used during my Kubernetes presentation

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    $ lein ring server
    
## Dockerify

    $ lein uberimage

## Running in Docker
    
    $ docker run --rm -p 3000:3000 gcr.io/parkster-env-stughelg/kubernetes-presentation:1.0.0

## Pushing to GKE Registry

    $ gcloud docker push gcr.io/parkster-env-stughelg/kubernetes-presentation:1.0.0
    
## Deploy to Kubernetes

    $ kubectl create -f kubernetes/rc.yaml
    $ kubectl create -f kubernetes/service.yaml
    $ kubectl get svc --watch # To wait for external IP

## Scaling
    
    $ kubectl scale --replicas=3 rc demo-1.0.0
    
## Rolling Update

    $ kubectl rolling-update --update-period="5s" demo-1.0.0 -f kubernetes/rc-2.0.0.yaml
     
 
## Curl on update 

    $ while true; do curl "http://<external ip>" ; echo ; sleep 0.5 ; done
    
## Canary Deployment

First scale the previous version of to 4 then create a new RC with version 2.0.0 with only 1 replica and deploy it without rolling update:
    
    $ kubectl create -f kubernetes/rc-2.0.0.yaml
    
Since the service routes on name "demo" only it'll the 2.0.0 on every 5th request! (one we're happy we can do a rolling update after having deleted the 2.0.0 rc)

## License

Copyright © 2016 Johan Haleby
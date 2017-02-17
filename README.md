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

If using mac you may first need to run `$(docker run sequenceiq/socat)` to create a proxy for port 2735. 

## Running in Docker
    
    $ docker run --rm -p 3000:3000 gcr.io/parkster-env-shared/kubernetes-presentation:1.0.0

## Pushing to GKE Registry

    $ gcloud docker -- push gcr.io/parkster-env-shared/kubernetes-presentation:1.0.0
    
## Deploy Pod to Kubernetes

    $ kubectl create -f kubernetes/deployment.yaml --record
    $ kubectl get deployments
    $ kubectl rollout status deployment/demo

## Deploy service to Kubernetes

    $ kubectl create -f kubernetes/service.yaml
    $ kubectl get svc --watch # To wait for external IP

Navigate to IP to see that everything works

## KubeUI

Run a proxy to the Kubernetes API server:

    $ kubectl proxy 

Navigate to [http://127.0.0.1:8001/ui](http://127.0.0.1:8001/ui) to see the UI

The Kubernetes API server validates and configures data for the api objects which include pods, services, replicationcontrollers, and others. 

## Scaling
    
    $ kubectl scale --replicas=3 deployments demo
    
## Curl on update 

    $ while true; do curl "http://<external ip>" ; echo ; sleep 0.5 ; done
    
## Canary Deployment

First scale the previous version of to 4 then create a new deployment with version 2.0.0 with only 1 replica and deploy it without rolling update:
    
    $ kubectl create -f kubernetes/deployment-canary.yaml --record
    
Since the service routes on name "demo" only it'll the 2.0.0 on every 5th request! (once we're happy we can do a rolling update after having deleted the 2.0.0 deployment)

## Rolling Update

    $ kubectl edit deployment demo

Change version to 2.0.0 and **remove selector matchLabel version** (otherwise it won't do rolling upgrade since it won't match the version label), 
save and watch the deployment rolling update:

    $ kubectl rollout status deployment/demo
        
## Revert back to revision 1:
   
Check rollback history:

    $ kubectl rollout history deployment/demo
    $ kubectl rollout history deployment/demo --revision=1 # more info
    
If we're not happy with version 2.0.0 we can rollback to version 1.0.0:

    $ kubectl rollout undo deployment/demo --to-revision=1
    
## License

Copyright © 2017 Johan Haleby
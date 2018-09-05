#!bin/bash
cd MovieCruiserAuthenticationService
mvn clean
sudo docker rmi user-service
cd ..
cd moviecruizerserverapp
mvn clean
docker rmi movie-service
cd ..
sudo docker rmi moviecruiser-client-image
mvn clean

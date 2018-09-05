#!bin/bash
cd MovieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package
docker build -t user-service .
cd ..
cd moviecruizerserverapp
source ./env-variable.sh
mvn clean package
docker build -t movie-service .
cd ..
cd moviecruizerclientapp
docker build -t moviecruiser-client-image .
cd ..

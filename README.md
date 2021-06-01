# new-spark-project

This project inserts and updates data into a mongo databse upon receiving HTTP PUT and GET requests.

To run the code successfully kindly follow these steps:
1. Create/Install a docker image of mongodb
2. Install Bazel
3. Create a docker container to connect to and run mongodb in it using this command: 
```
docker run --name myMongo -d -p 27017:27017 --name myMongo mongo
```
4. Run ProducerApp.java -- To do this, run the following commands: 
```
bazel build //:ProducerApp
bazel-bin/ProducerApp
```
5. Open a new terminal within the folder and run ConsumerApp.java (similar to step 4): 
```
bazel build //:ProducerApp
bazel-bin/ProducerApp
```
6. Open a new terminal within the folder and run Coordinator.java (similar to steps 4, 5):
```
bazel build //:ProducerApp
bazel-bin/ProducerApp
```
7. To view the database being updated in real time, run the following commands:
```
docker exec -it myMongo bash`
mongo
show dbs
use movies
coll = db.TopMoviesList
coll.find()
```
8. To stop the docker container from running, use the following command: 
```
sudo docker stop myMongo
``` 
9. In case you get the following error: Cannot kill container. unknown error after kill: runc did not terminate sucessfully. signaling init process caused "permission denied", 
Run the following commands: 
```
sudo aa-remove-unknown
docker container kill myMongo
``` 
10. To rerun the container(if required), use the following command: 
```
docker restart myMongo
```
11. Finally do `ctrl + C` to terminate ProducerApp.java and ConsumerApp.java 

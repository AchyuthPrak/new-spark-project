# new-spark-project

This project inserts and updates data into a mongo databse upon receiving HTTP PUT and GET requests.

To run the code successfully kindly follow these steps:
1. Create/Install a docker image of mongodb
2. Install Bazel
3. Create a docker container to connect to and run mongodb in it using this command: `docker run --name myMongo -d -p 27017:27017 --name myMongo mongo`
4. Run ProducerApp.java -- To do this, run the following commands: 
  1. `bazel build //:ProducerApp`
  2. `bazel-bin/ProducerApp`
5. Run ConsumerApp.java (similar to step 3): 
  1. `bazel build //:ConsumerApp`
  2. `bazel-bin/ConsumerApp`
6. Run Coordinator.java (similar to steps 3, 4):
  1. `bazel build //:Coordinator`
  2. `bazel-bin/Coordinator`
7. To view the database being updated in real time, run the following commands:
  1. `docker exec -it myMongo bash`
  2. `mongo`
  3. `show dbs`
  4. `use movies`
  5. `coll = db.TopMoviesList`
  6. `coll.find()`
8. To stop the docker container from running, use the following command: `sudo docker stop myMongo` 
9. In case you get the following error: Cannot kill container. unknown error after kill: runc did not terminate sucessfully. signaling init process caused "permission denied", 
Run the following commands: 
  1.`sudo aa-remove-unknown`
  2.`docker container kill myMongo` 
10. To rerun the container(if required), use the following command: docker restart myMongo

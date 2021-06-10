# new-spark-project

This project has 4 components: a producer, a consumer, a mongo database (mongoDB) and a coordinator. The producer, upon receiving a HTTP PUT request from the coordinator, puts the data into the mongoDB. Similarly, the consumer upon receiving a HTTP GET request from the coordinator, updates the relevant data in the mongoDB. The coordinator is an interactive application and the user can choose what to put to the mongoDB.

To run the code successfully kindly follow these steps:
1. Install mongoDB
2. Install Bazel
3. Connect to mongoDB using the following commands
```
sudo systemctl start mongodb
mongo
```
4. Run ProducerApp.java -- To do this, run the following commands: 
```
bazel run //:ProducerApp
```
5. Open a new terminal within the folder and run ConsumerApp.java (similar to step 4): 
```
bazel run //:ConsumerApp
```
6. Open a new terminal within the folder and run Coordinator.java (similar to steps 4, 5):
```
bazel run //:Coordinator
```
7. To view the database being updated in real time, run the following commands:
```
show dbs
use movies
coll = db.TopMoviesList
coll.find()
```
Optional: If required, before exiting the mongoDB, run this cmd to remove the database: `db.dropDatabase()`

8. To stop the mongoDB server from running, use the following commands: 
```
exit
sudo systemctl stop mongodb
``` 
9. Finally do `ctrl + C` to terminate ProducerApp.java and ConsumerApp.java 

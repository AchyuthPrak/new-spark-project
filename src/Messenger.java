import spark.Request;

public interface Messenger {
    void setUpMongoDb();
    String putToMongoDb(Request r);
}
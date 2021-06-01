import static spark.Spark.get;
import static spark.Spark.port;

public class ConsumerApp {
    public static void main(String[] args){
        port(StringConstants.consumerPort);
        Consumer c = new Consumer();
        c.setUpMongoDb();
        get("/updateEntry/:movie/:rating", (req, res) ->  c.putToMongoDb(req));
    }
}
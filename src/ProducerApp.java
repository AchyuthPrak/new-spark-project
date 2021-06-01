import static spark.Spark.port;
import static spark.Spark.put;

public class ProducerApp {
    public static void main(String[] args){
        port(StringConstants.producerPort);
        Producer p = new Producer();
        p.setUpMongoDb();
        put("/newEntry", (req, res) ->  p.putToMongoDb(req));
    }
}
import static spark.Spark.port;
import static spark.Spark.put;

public class ProducerApp {
    public static void main(String[] args) {
        port(StringConstants.producerPort);
        Producer p;
        if(args.length == 0)
            p = new Producer();
        else
            p = new Producer(args[0]);
        p.setUpMongoDb();
        put("/newEntry", (req, res) ->  p.putToMongoDb(req));
    }
}
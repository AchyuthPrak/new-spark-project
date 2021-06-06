import java.net.UnknownHostException;
import static spark.Spark.get;
import static spark.Spark.port;

public class ConsumerApp {
    public static void main(String[] args) throws UnknownHostException {
        port(StringConstants.consumerPort);
        Consumer c;
        if(args.length == 0)
            c = new Consumer();
        else
            c = new Consumer(args[0]);
        c.setUpMongoDb();
        get("/updateEntry/:movie/:rating", (req, res) ->  c.putToMongoDb(req));
    }
}
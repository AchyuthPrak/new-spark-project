import java.net.InetAddress;
import java.net.UnknownHostException;

import static spark.Spark.get;
import static spark.Spark.port;

public class ConsumerApp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Consumer IP Address:- " + inetAddress.getHostAddress());
        port(StringConstants.consumerPort);
        Consumer c = new Consumer();
        c.setUpMongoDb();
        get("/updateEntry/:movie/:rating", (req, res) ->  c.putToMongoDb(req));
    }
}
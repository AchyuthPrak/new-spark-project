import static spark.Spark.port;
import static spark.Spark.put;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ProducerApp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Producer IP Address:- " + inetAddress.getHostAddress());
        port(StringConstants.producerPort);
        Producer p = new Producer();
        p.setUpMongoDb();
        put("/newEntry", (req, res) ->  p.putToMongoDb(req));
    }
}
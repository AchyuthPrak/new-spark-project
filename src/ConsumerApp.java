import java.net.UnknownHostException;
import static spark.Spark.get;
import static spark.Spark.port;
import java.net.InetAddress;

public class ConsumerApp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        port(StringConstants.consumerPort);
        Consumer c;
        if(args.length == 0)
            c = new Consumer();
        else
            c = new Consumer(args[0], Integer.parseInt(args[1]));
        c.setUpMongoDb();
        get("/updateEntry/:movie/:rating", (req, res) ->  c.putToMongoDb(req));
    }
}
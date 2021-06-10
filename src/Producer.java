import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import spark.Request;

public class Producer implements Messenger {
    private MongoCollection mongoCollection;
    private MongoDatabase db;
    private final String mongoHost;
    private final int mongoPort;
    Producer(){
        mongoHost = StringConstants.mongoDbHost;
        mongoPort = StringConstants.mongoDbPort;
    }
    Producer(String str, int i){
        mongoHost = str;
        mongoPort = i;
    }
    @Override
    public void setUpMongoDb() {
        MongoClient mongoClient = new MongoClient(mongoHost, mongoPort);
        db = mongoClient.getDatabase(StringConstants.database);
        mongoCollection = db.getCollection(StringConstants.collection);
    }

    @Override
    public String putToMongoDb(Request r) {
        String movieT, movieR;
        //"{movie= "+title+"& rating= "+rating+"}"
        String str = r.body();
        String[] arrOfStr = str.split("&", 2);
        String[] titleUtil = arrOfStr[0].split("=", 2);
        String[] ratingUtil = arrOfStr[1].split("=",2);
        movieT = titleUtil[1].substring(1);
        int len = ratingUtil[1].length();
        movieR = ratingUtil[1].substring(1, len - 1);

        Document document = new Document();
        document.append("Title", movieT);
        document.append("Rating", movieR);
        document.append("Processed", "FALSE");
        mongoCollection.insertOne(document);

        return "Successfully added " + movieT + " " + movieR + " to Database " + db.getName() + " and collection " + StringConstants.collection + "\n";
    }
}
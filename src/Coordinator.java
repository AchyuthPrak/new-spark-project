import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Coordinator {
    private static final ArrayList<String> moviesList = new ArrayList<>();
    private static final ArrayList<String> ratingsList = new ArrayList<>();
    public static void initialize(){
        org.jsoup.nodes.Document document = null;
        try {
            document = Jsoup.connect("http://www.imdb.com/chart/top").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert document != null;
        for (Element row : document.select("table.chart.full-width tr")) {
            final String title = row.select(".titleColumn a").text();
            final String rating = row.select(".imdbRating").text();
            moviesList.add(title);
            ratingsList.add(rating);
        }
    }

    public static void putRequest(int i) throws IOException {
        URL url = new URL ("http://localhost:"+StringConstants.producerPort+"/newEntry");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String title, rating;
        title = moviesList.get(i); rating = ratingsList.get(i);
        String jsonInputString = "{movie= "+title+"& rating= "+rating+"}";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }

    public static void getRequest(String movie, String rating) throws IOException, InterruptedException {
        movie = movie.replaceAll("\\s", "%20");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:"+StringConstants.consumerPort+"/updateEntry/"+movie+"/"+rating))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        initialize();
        for(int i = 1; i<=5; i++){
            putRequest(i);
            TimeUnit.SECONDS.sleep(30);
            getRequest(moviesList.get(i), ratingsList.get(i));
            TimeUnit.SECONDS.sleep(30);
        }
    }
}
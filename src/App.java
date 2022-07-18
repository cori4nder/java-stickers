import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Chamada API - Conexão HTTP
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        URI adress = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // Verificar o corpo do JSON - System.out.println(body);
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // System.out.println(movieList.size());
        System.out.println(movieList.get(0));

        for (Map<String, String> movie : movieList) {
            System.out.println(movie.get("rank") + " - " + movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}

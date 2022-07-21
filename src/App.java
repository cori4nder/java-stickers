import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        // System.out.println(movieList.get(0));

        var generator = new StickerGenerator();

        for (Map<String, String> movie : movieList) {
            String urlImage = movie.get("image");
            String title = movie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String nameArchive = title + ".png";

            generator.create(inputStream, nameArchive);

            System.out.println(
                    "\u001b[37;1m\u001b[45;1m" + " " + movie.get("rank") + " | Título" + " - " + movie.get("title")
                            + " "
                            + "\u001b[m");
            System.out.println("URL Img - " + movie.get("image"));

            System.out.print("Classificação - " + movie.get("imDbRating") + " - ");

            Float.parseFloat(movie.get("imDbRating"));

            Float star = Float.parseFloat(movie.get("imDbRating"));

            for (int i = 0; i < Math.round(star); i++) {
                String string = "💫";
                string = string.replaceAll("[\u1F4A5]", "grinningF");

                System.out.print(string);
            }

            System.out.println();
            System.out.println();
        }

    }
}

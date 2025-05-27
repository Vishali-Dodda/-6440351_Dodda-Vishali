import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientExample {
    public static void main(String[] args) throws Exception {
        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest (GET request to GitHub API for user info)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/users/octocat"))
                .GET()
                .build();

        // Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print status code and response body
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body:");
        System.out.println(response.body());
    }
}

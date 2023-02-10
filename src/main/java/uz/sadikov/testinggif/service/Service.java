package uz.sadikov.testinggif.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;


@org.springframework.stereotype.Service
public class Service {
    @Value("${currency_api_key}")
    private String currency_api_key;

    @Value("${api_key_giffy}")
    String api_key;

    public double getPrevCurrency() throws IOException, InterruptedException {
        LocalDate time = LocalDate.now().minusDays(1);
        String data = String.valueOf(time);
        String URl = "https://openexchangerates.org/api/historical/" + data + ".json?app_id=" + currency_api_key + "&base=USD&symbols=RUB&show_alternative=false&prettyprint=false";
        HttpResponse<String> response = getResponseFromUrl(URl);
        JSONObject obj = new JSONObject(response.body());
        return obj.getJSONObject("rates").getDouble("RUB");
    }

    public double getNewCurrency() throws IOException, InterruptedException {
        String URL = "https://openexchangerates.org/api/latest.json?app_id=" + currency_api_key + "&base=USD&symbols=RUB&prettyprint=false&show_alternative=true";
        HttpResponse<String> response = getResponseFromUrl(URL);
        JSONObject obj = new JSONObject(response.body());
        return obj.getJSONObject("rates").getDouble("RUB");

    }

    public boolean isIncreased() throws IOException, InterruptedException {
        double prev = getPrevCurrency();
        double current = getNewCurrency();
        return prev <= current;
    }

    public String getGif() throws IOException, InterruptedException {
        String tag;


        if (isIncreased()) {
            tag = "broke";
        } else tag = "rich";
        String url = "https://api.giphy.com/v1/gifs/random?api_key=" + api_key + "&tag=" + tag + "&rating=g";
        HttpResponse<String> response = getResponseFromUrl(url);
        JSONObject obj = new JSONObject(response.body());
        return obj.getJSONObject("data").getString("url");
    }

    private HttpResponse<String> getResponseFromUrl(String URL) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    }

}

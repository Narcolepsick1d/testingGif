package uz.sadikov.testinggif.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.sadikov.testinggif.util.GetResponser;

import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public class ServiceGiffy {


    @Value("${api_key_giffy}")
    String api_key;


    GetResponser responser = new GetResponser();


    public String getGif(boolean isIncreased) throws IOException, InterruptedException {
        String tag;


        if (isIncreased) {
            tag = "poor";
        } else tag = "rich";
        String url =String.format("https://api.giphy.com/v1/gifs/random?api_key=%s&tag=%s&rating=g",api_key,tag);
        HttpResponse<String> response = responser.getResponseFromUrl(url);
        JSONObject obj = new JSONObject(response.body());
        return obj.getJSONObject("data").getString("url");
    }
}

package uz.sadikov.testinggif.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import uz.sadikov.testinggif.util.GetResponser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDate;


@org.springframework.stereotype.Service
public class ServiceGetCurrency {
    @Value("${currency_api_key}")
    private String currency_api_key;
    GetResponser responser = new GetResponser();

    public boolean isIncreased(String currency) throws IOException, InterruptedException {

        LocalDate time = LocalDate.now().minusDays(1);
        String data = String.valueOf(time);
        String URl = String.format("https://openexchangerates.org/api/historical/%s.json?app_id=%s",data,currency_api_key);
        HttpResponse<String> response = responser.getResponseFromUrl(URl);
        JSONObject obj = new JSONObject(response.body());
        double prev = obj.getJSONObject("rates").getDouble(currency);

        String URL =String.format("https://openexchangerates.org/api/latest.json?app_id=%s",currency_api_key);
        response = responser.getResponseFromUrl(URL);
        obj = new JSONObject(response.body());
        double current = obj.getJSONObject("rates").getDouble(currency);

        return prev <= current;
    }


}

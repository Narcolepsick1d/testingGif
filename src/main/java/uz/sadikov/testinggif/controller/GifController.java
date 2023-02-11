package uz.sadikov.testinggif.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.sadikov.testinggif.service.ServiceGetCurrency;
import uz.sadikov.testinggif.service.ServiceGiffy;

import java.io.IOException;

@RestController
public class GifController {
    @Autowired
    public ServiceGiffy getGifService;
    @Autowired
    public ServiceGetCurrency currencyService;

    @GetMapping("/change/{currency}")
    public String getGiffy(@PathVariable("currency") String currency) throws IOException, InterruptedException {
        boolean isIncreased = currencyService.isIncreased(currency);
        return getGifService.getGif(isIncreased);
    }
}

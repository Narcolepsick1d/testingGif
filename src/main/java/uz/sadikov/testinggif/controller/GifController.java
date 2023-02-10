package uz.sadikov.testinggif.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sadikov.testinggif.service.Service;

import java.io.IOException;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class GifController {
    @Autowired
    private Service service;

    @GetMapping("/hi")
    public String getGiffy() throws IOException, InterruptedException {
        return service.getGif();
    }
}

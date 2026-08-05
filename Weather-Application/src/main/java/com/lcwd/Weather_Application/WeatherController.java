package com.lcwd.Weather_Application;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/weather")
public class WeatherController {

    private final String API_KEY = "bc1dfb5745b884831e0683cb62ef8040";

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}

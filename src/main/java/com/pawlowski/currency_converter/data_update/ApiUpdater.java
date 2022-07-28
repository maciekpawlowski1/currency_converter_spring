package com.pawlowski.currency_converter.data_update;

import com.pawlowski.currency_converter.dao.CurrencyService;
import com.pawlowski.currency_converter.entities.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Component
public class ApiUpdater {


    @Autowired
    public ApiUpdater(RestTemplate restTemplate, CurrencyService currencyService) {
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }

    final RestTemplate restTemplate;

    final CurrencyService currencyService;
    @Value("${rapidApi.apiKey}")
    String apiKey;

    @Value("${rapidApi.url}")
    String url;

    public RatesResponse updateData()
    {
        ResponseEntity<RatesResponse> response = makeRequest();
        if(response.getStatusCode() == HttpStatus.OK)
        {
            System.out.println("Request success");
            if(response.getBody() != null)
            {
                List<ExchangeRate> newRates = new ArrayList<>();
                System.out.println(response.getBody().toString());
                RatesResponse body = response.getBody();
                body.rates.forEach(new BiConsumer<String, Double>() {
                    @Override
                    public void accept(String s, Double aDouble) {
                        newRates.add(new ExchangeRate(body.base, s, body.date, aDouble));
                    }
                });
                if(!newRates.isEmpty())
                {
                    currencyService.updateData(newRates);
                }
            }
            else
            {
                System.out.println("Response is null");
            }
        }
        else
        {
            System.out.println("Request failed");
        }
        return response.getBody();
    }

    private ResponseEntity<RatesResponse> makeRequest()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);

        HttpEntity request = new HttpEntity(headers);

        return  restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                RatesResponse.class
        );

    }
}

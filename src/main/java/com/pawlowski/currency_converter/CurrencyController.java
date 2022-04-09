package com.pawlowski.currency_converter;

import com.pawlowski.currency_converter.dao.CurrencyService;
import com.pawlowski.currency_converter.data_update.ApiUpdater;
import com.pawlowski.currency_converter.data_update.RatesResponse;
import com.pawlowski.currency_converter.entities.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    ApiUpdater apiUpdater;

    @GetMapping("/rates")
    public List<ExchangeRate> getAllRates()
    {
        return currencyService.getAllExchangeRates();
    }

    @GetMapping("/rates/{currency}")
    public ExchangeRate getSpecificRate(@PathVariable String currency)
    {
        return currencyService.getExchangeRate(currency);
    }

    @PostMapping("/updated")
    public RatesResponse updateData()
    {
        return apiUpdater.updateData();
    }


}

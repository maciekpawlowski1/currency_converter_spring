package com.pawlowski.currency_converter;

import com.pawlowski.currency_converter.dao.CurrencyService;
import com.pawlowski.currency_converter.data_update.ApiUpdater;
import com.pawlowski.currency_converter.data_update.RatesResponse;
import com.pawlowski.currency_converter.data_update.UpdateTimeChecker;
import com.pawlowski.currency_converter.entities.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    public CurrencyController(CurrencyService currencyService, ApiUpdater apiUpdater, UpdateTimeChecker updateTimeChecker) {
        this.currencyService = currencyService;
        this.apiUpdater = apiUpdater;
        this.updateTimeChecker = updateTimeChecker;
    }

    final CurrencyService currencyService;

    final ApiUpdater apiUpdater;

    final UpdateTimeChecker updateTimeChecker;

    @GetMapping("/rates")
    public List<ExchangeRate> getAllRates()
    {
        updateTimeChecker.doSomethingIfWasNotTodayUpdated(new UpdateTimeChecker.UpdateAction() {
            @Override
            public void invoke() {
                System.out.println("Updating rates...");
                updateData();
            }
        });
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

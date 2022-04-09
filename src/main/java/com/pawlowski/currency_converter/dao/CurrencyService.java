package com.pawlowski.currency_converter.dao;

import com.pawlowski.currency_converter.entities.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public List<ExchangeRate> getAllExchangeRates()
    {
        return currencyRepository.findAll();
    }

    public ExchangeRate getExchangeRate(String currency)
    {
        return currencyRepository.findByTo(currency);
    }

    @Transactional
    public void updateData(List<ExchangeRate> exchangeRates)
    {
        currencyRepository.deleteAll();
        currencyRepository.saveAll(exchangeRates);
    }
}

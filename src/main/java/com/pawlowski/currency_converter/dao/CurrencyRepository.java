package com.pawlowski.currency_converter.dao;

import com.pawlowski.currency_converter.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<ExchangeRate, Integer> {
    ExchangeRate findByTo(String to);
}

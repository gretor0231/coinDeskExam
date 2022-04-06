package com.bezkoder.spring.jpa.h2.services;

import com.bezkoder.spring.jpa.h2.model.MyCurrency;

import java.util.List;

public interface CurrencyService {

    List<MyCurrency> findCoinDesk();

    boolean addCurrency(String symbol);

    MyCurrency updateCurrency(String id, MyCurrency currency);

    MyCurrency getCurrency(String symbol);

    boolean deleteCurrency(String id);

}

package com.bezkoder.spring.jpa.h2.services;

import com.bezkoder.spring.jpa.h2.model.MyCurrency;
import com.bezkoder.spring.jpa.h2.repository.MyCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    private MyCurrencyRepository myCurrencyRepository;

    @Autowired
    public CurrencyServiceImpl(MyCurrencyRepository myCurrencyRepository) {
        this.myCurrencyRepository = myCurrencyRepository;
    }


    @Override
    public List<MyCurrency> findCoinDesk() {
        String websiteResponse = "https://api.coindesk.com/v1/bpi/currentprice.json";
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(websiteResponse, String.class);
        JSONObject obj = new JSONObject(jsonString);
        JSONObject objTwo = obj.getJSONObject("bpi");
        List<MyCurrency> result = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for(String keyString : objTwo.keySet()){
            MyCurrency myCurrency = new MyCurrency();
            String code = objTwo.getJSONObject(keyString).getString("code");
            myCurrency.setSymbol(code);
            String description = objTwo.getJSONObject(keyString).getString("description");
            if(description.equals("Euro")){
                description = "歐元";
            }else if(description.equals("British Pound Sterling")){
                description = "英鎊";
            }else if (description.equals("United States Dollar")){
                description ="美金";
            }
            myCurrency.setDescription(description);
            String rate = objTwo.getJSONObject(keyString).getString("rate");
            myCurrency.setRate(rate);
            myCurrency.setUpdateTime(time);
            result.add(myCurrency);
        }
        return result;
    }

    @Override
    public boolean addCurrency(String symbol) {
        List<MyCurrency> currencies = findCoinDesk();
        for(MyCurrency currency : currencies){
            if(currency.getSymbol().equals(symbol)){
                myCurrencyRepository.save(currency);
                return true;
            }
        }
        return false;
    }

    @Override
    public MyCurrency updateCurrency(String id, MyCurrency oldCurrency) {
        Optional<MyCurrency> currencyData = myCurrencyRepository.findById(Long.valueOf(id));

        if (currencyData.isPresent()) {
            MyCurrency currency = currencyData.get();
            currency.setSymbol(oldCurrency.getSymbol());
            currency.setDescription(oldCurrency.getDescription());
            currency.setRate(oldCurrency.getRate());
            currency.setUpdateTime(oldCurrency.getUpdateTime());
            myCurrencyRepository.save(currency);
            return currency;
        }
        return null;
    }

    @Override
    public MyCurrency getCurrency(String symbol) {

        List<MyCurrency> currencies = myCurrencyRepository.findAll();
        for (MyCurrency currency : currencies){
            if(currency.getSymbol().equals(symbol)){
                return currency;
            }
        }
        return null;
    }

    @Override
    public boolean deleteCurrency(String id) {
        if(!myCurrencyRepository.findById(Long.valueOf(id)).isPresent()){
            return false;
        }
        myCurrencyRepository.deleteById(Long.valueOf(id));
        return true;
    }


}

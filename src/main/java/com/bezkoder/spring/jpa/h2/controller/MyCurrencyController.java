package com.bezkoder.spring.jpa.h2.controller;


import com.bezkoder.spring.jpa.h2.model.MyCurrency;
import com.bezkoder.spring.jpa.h2.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MyCurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public MyCurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/coindesk")
    public ResponseEntity<List<MyCurrency>> getCoinDeskAPI() {
        try {
            List<MyCurrency> currencies  = currencyService.findCoinDesk();
            if (currencies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(currencies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




//    boolean addCurrency(String symbol);
//
//    MyCurrency updateCurrency(String symbol);
//
//    MyCurrency getCurrency(String symbol);
//
//    boolean deleteCurrency(String symbol);




}

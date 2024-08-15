package com.samsung.demo2.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("RateService2")
public class RateService2 extends ServiceBase implements IRateService{
    @Override
    public Double convertRate(String currency, int amount) {
        return null;
    }
}

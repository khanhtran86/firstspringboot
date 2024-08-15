package com.samsung.demo2.controller;

import com.samsung.demo2.common.OK;
import com.samsung.demo2.common.Response;
import com.samsung.demo2.repository.models.RateInfo;
import com.samsung.demo2.services.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ConvertorController {
    IRateService rateService;
    public ConvertorController(@Qualifier("RateService") IRateService rateService)
    {
        this.rateService = rateService;
    }


    @GetMapping("/convert/{source}/{amount}")
    public ResponseEntity convert(@PathVariable(name = "source") String source,
                                  @PathVariable(name = "amount")Integer amount)
    {
        Double amountConverted = this.rateService.convertRate(source, amount);

        return ResponseEntity.ok(amountConverted);
    }
}

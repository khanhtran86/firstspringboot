package com.samsung.demo2.services;

import com.samsung.demo2.repository.models.RateInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service //Bean to indicate this is a spring boot service
@Qualifier("RateService")
public class RateService extends ServiceBase implements IRateService{
    private List<RateInfo> getListOfRate()
    {
        URL url = null;
        try {
            url = new URL("https://portal.vietcombank.com.vn/Usercontrols/TVPortal.TyGia/pXML.aspx");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //Define regex
            Pattern ratePattern = Pattern.compile("<Exrate CurrencyCode=\"(.+?)\" .*Sell=\"(.+?)\"");
            List<RateInfo> lstRates = new ArrayList<>();

            String line = "";
            while ((line = reader.readLine())!=null) {
                Matcher matcher = ratePattern.matcher(line);
                if(matcher.find())
                {
                    RateInfo rate = new RateInfo();
                    rate.CurrencyCode = matcher.group(1);
                    rate.Rate = Double.parseDouble(matcher.group(2).replaceAll(",",""));

                    lstRates.add(rate);
                }
            }

            return lstRates;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double convertRate(String currency, int amount) {
        //Get fullrate from VCB
        List<RateInfo> lstRates =getListOfRate();

        //Filter rate matched with inputted currency
        List<RateInfo> lstFilterd = lstRates.stream().filter((r)->r.CurrencyCode.equalsIgnoreCase(currency)).collect(Collectors.toList());

        //If available then get first to convert
        if(lstFilterd.size()>0)
        {
            return amount*lstFilterd.get(0).Rate;
        }
        return 0d;
    }
}

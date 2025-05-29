package org.example.analiticproviderservice.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AnalyticalParser {

    public List<String> parseRequestsToStrings(List<String> requests) {
        if (requests.isEmpty()) {
            requests.add(null);
        }
        return requests;
    }

    public List<String> parseOperationsToStrings(List<String> operations) {
        if (operations.isEmpty()) {
            operations.add(null);
        }
        return operations;
    }

    public List<Date> parseDates(List<String> datesStrings){
        if (datesStrings.isEmpty()) {
            List<Date> dates = new ArrayList<>();
            dates.add(null);
            return dates;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return datesStrings.stream()
                    .map(date -> {
                        try {
                            return format.parse(date);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
        }
    }

    public List<String> parseProvidersToStrings(List<String> providers) {
        if (providers.isEmpty()) {
            providers.add(null);
        }
        return providers;
    }

    public List<Double> parsePrices(List<String> pricesStrings) {
        if (pricesStrings.isEmpty()) {
            List<Double> prices = new ArrayList<>();
            prices.add(null);
            return prices;
        } else {
            return pricesStrings.stream()
                    .map(Double::parseDouble)
                    .toList();
        }

    }
}

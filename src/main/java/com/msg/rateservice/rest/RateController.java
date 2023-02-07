package com.msg.rateservice.rest;

import com.msg.rateservice.domain.Rate;
import com.msg.rateservice.domain.dto.RateResponse;
import com.msg.rateservice.service.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RateController {

    private RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/get-highest-standard-rates")
    public RateResponse getHighestStandardRates(@RequestParam(value = "listSize", required = false, defaultValue = "3") int listSize) {
        List<Rate> rates = rateService.getHighestStandardRates();

        if (listSize > rates.size()) {
            return new RateResponse("Invalid list size", RateResponse.RateStatus.FAILED, null);
        }

        return new RateResponse(
                "Success",
                RateResponse.RateStatus.OK,
                rates.subList(rates.size() - listSize, rates.size()));
    }

    @GetMapping("/get-lowest-reduced-rates")
    public RateResponse getLowestReducedRates(@RequestParam(value = "listSize", required = false, defaultValue = "3") int listSize) {
        List<Rate> rates = rateService.getLowestReducedRates();
        Collections.reverse(rates);

        if (listSize > rates.size()) {
            return new RateResponse("Invalid list size", RateResponse.RateStatus.FAILED, null);
        }

        return new RateResponse(
                "Success",
                RateResponse.RateStatus.OK,
                rates.subList(rates.size() - listSize, rates.size()));
    }

}

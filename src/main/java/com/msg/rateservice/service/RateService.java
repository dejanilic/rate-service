package com.msg.rateservice.service;

import com.msg.rateservice.domain.Rate;

import java.util.List;

public interface RateService {

    List<Rate> getHighestStandardRates();

    List<Rate> getLowestReducedRates();

}

package com.msg.rateservice.service.impl;

import com.google.common.collect.Lists;
import com.msg.rateservice.domain.Rate;
import com.msg.rateservice.repository.RateRepository;
import com.msg.rateservice.service.RateService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> getHighestStandardRates() {
        List<Rate> rates = Lists.newArrayList(rateRepository.findAll());
        rates.sort(Comparator.comparing(Rate::getStandardRate));
        return rates;
    }

    @Override
    public List<Rate> getLowestReducedRates() {
        List<Rate> rates = Lists.newArrayList(rateRepository.findAll());
        rates.sort(Comparator.comparing(Rate::getReducedRate));
        return rates;
    }
}

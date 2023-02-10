package com.msg.rateservice.service.impl;

import com.google.common.collect.Lists;
import com.msg.rateservice.domain.Rate;
import com.msg.rateservice.repository.RateRepository;
import com.msg.rateservice.service.RateException;
import com.msg.rateservice.service.RateService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.msg.rateservice.service.RateException.RateExceptionCode.RATE_FETCH_EXCEPTION;

@Service
public class RateServiceImpl implements RateService {

    private RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> getHighestStandardRates() {

        try {
            List<Rate> rates = Lists.newArrayList(rateRepository.findAll());
            rates.sort(Comparator.comparing(Rate::getStandardRate));
            return rates;
        } catch (Exception e) {
            throw new RateException(RATE_FETCH_EXCEPTION, "Rates fetching failed.", (Object) null);
        }

    }

    @Override
    public List<Rate> getLowestReducedRates() {
        List<Rate> rates = Lists.newArrayList(rateRepository.findAll());
        rates.sort(Comparator.comparing(Rate::getReducedRate));
        return rates;
    }
}

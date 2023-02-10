package com.msg.rateservice.service;

import com.msg.rateservice.domain.Rate;

import java.util.List;

public interface RateService {

    /**
     * Returns a list of {@link Rate} objects.
     * Objects are sorted by standardRate field.
     *
     */
    List<Rate> getHighestStandardRates();

    /**
     * Returns a list of {@link Rate} objects.
     * Objects are sorted by reducedRate field.
     *
     */
    List<Rate> getLowestReducedRates();

}

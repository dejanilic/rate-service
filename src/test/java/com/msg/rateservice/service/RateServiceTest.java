package com.msg.rateservice.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.given;

import com.msg.rateservice.RateServiceApplicationTests;
import com.msg.rateservice.domain.Rate;
import com.msg.rateservice.repository.RateRepository;
import com.msg.rateservice.service.impl.RateServiceImpl;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RateServiceTest extends RateServiceApplicationTests {

    @InjectMocks
    private RateServiceImpl rateService;

    @Mock
    private RateRepository rateRepository;

    @Test
    public void testGetHighestStandardRates() {
        Rate rate = Rate.builder()
                .id(1L)
                .standardRate(20.0)
                .reducedRate(15.0)
                .build();

        given(rateRepository.findAll()).willReturn(Arrays.asList(rate));

        List<Rate> rates = rateService.getHighestStandardRates();

        assertThat(rates.size(), is(1));
        assertThat(rates.get(0).getId(), is(1L));
        assertThat(rates.get(0).getStandardRate(), is(20.0));
        assertThat(rates.get(0).getReducedRate(), is(15.0));
    }

    @Ignore
    public void testGetLowestReducedRates() {
        // same as the test above...
    }

}

package com.msg.rateservice.rest;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import com.msg.rateservice.domain.Rate;
import com.msg.rateservice.service.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(RateController.class)
@ExtendWith(MockitoExtension.class)
public class RateControllerTest {

    @Autowired
    // IntelliJ IDEA 2021.2.3 is giving me compile error, so I have to suppress it
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mvc;

    @MockBean
    private RateService rateService;

    private List<Rate> rates = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Rate rate1 = Rate.builder().id(1L).standardRate(20.0).reducedRate(15.0).build();
        Rate rate2 = Rate.builder().id(2L).standardRate(10.0).reducedRate(5.0).build();
        Rate rate3 = Rate.builder().id(3L).standardRate(25.0).reducedRate(20.0).build();
        Rate rate4 = Rate.builder().id(4L).standardRate(30.0).reducedRate(29.0).build();

        rates.add(rate1);
        rates.add(rate2);
        rates.add(rate3);
        rates.add(rate4);
    }

    @Test
    public void testGetHighestStandardRates() throws Exception {
        given(rateService.getHighestStandardRates()).willReturn(rates);

        mvc.perform(MockMvcRequestBuilders.get("/api/get-highest-standard-rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Success")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rates.length()", is(3))); // 3 is default list size

    }

}



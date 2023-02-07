package com.msg.rateservice.domain.dto;

import com.msg.rateservice.domain.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RateResponse {

    public enum RateStatus {
        OK, FAILED
    }

    private String message;

    private RateStatus status;

    private List<Rate> rates;

    public RateResponse(RateStatus status, List<Rate> rates) {
        this.status = status;
        this.rates = rates;
    }
}

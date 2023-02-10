package com.msg.rateservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

/**
 * EU VAT rate POJO class
 *
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String country;

    @Column
    @JsonProperty("standard_rate")
    private Double standardRate;

    @Column
    @JsonProperty("reduced_rate")
    private Double reducedRate;

    @Column
    @JsonProperty("reduced_rate_alt")
    private Double reducedRateAlt;

    @Column
    @JsonProperty("super_reduced_rate")
    private Boolean superReducedRate;

    @Column
    @JsonProperty("parking_rate")
    private Double parkingRate;

}

package com.msg.rateservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msg.rateservice.domain.Rate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.msg.rateservice.repository.RateRepository;
import com.msg.rateservice.service.RateService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class RateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateServiceApplication.class, args);
    }
}

@Component
@Profile("!test")
class DataLoader implements ApplicationRunner {

    private RateRepository rateRepository;

    public DataLoader(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Rate>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/rates.json");
        try {
            List<Rate> rates = mapper.readValue(inputStream,typeReference);
            rateRepository.saveAll(rates);
        } catch (IOException e){
            System.out.println("Unable to save rates: " + e.getMessage());
        }
    }
}


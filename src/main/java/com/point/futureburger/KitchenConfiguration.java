package com.point.futureburger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class KitchenConfiguration {

    @Bean
    public List<UUID> certifiedOriginalFood() {
        return new ArrayList<>();
    }
}

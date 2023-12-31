package com.ricodev.shopsService.configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigs {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
   public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}

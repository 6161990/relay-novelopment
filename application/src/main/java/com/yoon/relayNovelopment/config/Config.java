package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.domain.ParentNovelKeyFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;

@Configuration
public class Config {

    @Bean
    public AlternativeJdkIdGenerator alternativeJdkIdGenerator(){
        return new AlternativeJdkIdGenerator();
    }

    @Bean
    public ParentNovelKeyFinder parentNovelKeyFinder(){
        return new ParentNovelKeyFinder();
    }
}

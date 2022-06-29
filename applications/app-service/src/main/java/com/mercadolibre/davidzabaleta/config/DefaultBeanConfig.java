package com.mercadolibre.davidzabaleta.config;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class DefaultBeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    private final MutantDnaRepository mutantDnaRepository = mutantDna -> Mono.just(MutantDna.builder().build());

    @Bean
    @ConditionalOnMissingBean
    public MutantDnaRepository mutantDnaRepository() {
        return mutantDnaRepository;
    }

}

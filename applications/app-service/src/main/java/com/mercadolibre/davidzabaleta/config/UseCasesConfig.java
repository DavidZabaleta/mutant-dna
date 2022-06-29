package com.mercadolibre.davidzabaleta.config;

import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import com.mercadolibre.davidzabaleta.usecase.mutant.MutantDnaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
public class UseCasesConfig {

        @Bean
        public MutantDnaUseCase mutantDnaUseCase(MutantDnaRepository mutantDnaRepository) {
                return new MutantDnaUseCase(mutantDnaRepository);
        }
}

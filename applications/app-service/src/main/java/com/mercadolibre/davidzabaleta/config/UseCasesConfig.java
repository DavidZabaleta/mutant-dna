package com.mercadolibre.davidzabaleta.config;

import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import com.mercadolibre.davidzabaleta.usecase.mutant.MutantDnaUseCase;
import com.mercadolibre.davidzabaleta.usecase.stats.DnaStatsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

        @Bean
        public MutantDnaUseCase mutantDnaUseCase(MutantDnaRepository mutantDnaRepository) {
                return new MutantDnaUseCase(mutantDnaRepository);
        }

        @Bean
        public DnaStatsUseCase dnaStatsUseCase(MutantDnaRepository mutantDnaRepository) {
                return new DnaStatsUseCase(mutantDnaRepository);
        }
}

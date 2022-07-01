package com.mercadolibre.davidzabaleta.usecase.stats;

import com.mercadolibre.davidzabaleta.model.DnaStats;
import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import com.mercadolibre.davidzabaleta.usecase.stats.operations.OperationsDnaStatsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DnaStatsUseCase {

    private final MutantDnaRepository dnaStatsRepository;

    public Mono<DnaStats> getDnaStats() {
        return dnaStatsRepository.getAllDnaResults()
                .collectList()
                .map(OperationsDnaStatsUseCase::calculateDnaStats);
    }
}

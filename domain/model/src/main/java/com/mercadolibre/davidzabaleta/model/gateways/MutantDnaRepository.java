package com.mercadolibre.davidzabaleta.model.gateways;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MutantDnaRepository {
    Mono<MutantDna> saveRecord(MutantDna mutantDna);
    Flux<MutantDna> getAllDnaResults();
}

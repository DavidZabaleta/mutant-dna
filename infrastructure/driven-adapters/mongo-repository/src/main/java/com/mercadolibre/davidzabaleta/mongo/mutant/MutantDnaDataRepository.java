package com.mercadolibre.davidzabaleta.mongo.mutant;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MutantDnaDataRepository extends ReactiveCrudRepository<MutantDnaData, String>, ReactiveQueryByExampleExecutor<MutantDnaData> {
}

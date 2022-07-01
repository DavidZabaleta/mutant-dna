package com.mercadolibre.davidzabaleta.mongo.mutant;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import com.mercadolibre.davidzabaleta.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MutantDnaRepositoryAdapter extends AdapterOperations<MutantDna, MutantDnaData, String, MutantDnaDataRepository>
        implements MutantDnaRepository {

    public MutantDnaRepositoryAdapter(MutantDnaDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, MutantDnaConverter::convertDataToModel);
    }

    @Override
    public Mono<MutantDna> saveRecord(MutantDna mutantDna) {
        return repository.save(MutantDnaConverter.convertModelToData(mutantDna))
                .map(MutantDnaConverter::convertDataToModel);
    }

    @Override
    public Flux<MutantDna> getAllDnaResults() {
        return findAll();
    }
}

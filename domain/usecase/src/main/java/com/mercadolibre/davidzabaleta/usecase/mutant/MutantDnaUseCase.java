package com.mercadolibre.davidzabaleta.usecase.mutant;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import com.mercadolibre.davidzabaleta.model.exceptions.MutantDnaException;
import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import com.mercadolibre.davidzabaleta.usecase.mutant.operations.OperationsMutantDna;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static com.mercadolibre.davidzabaleta.usecase.mutant.operations.OperationsMutantDna.containsNotAllowedNitrogenousBases;
import static com.mercadolibre.davidzabaleta.usecase.mutant.operations.OperationsMutantDna.validateStructure;

@RequiredArgsConstructor
public class MutantDnaUseCase {

    private final MutantDnaRepository mutantDnaRepository;

    public Mono<Boolean> isMutant(List<String> dnaSequence) {
        return Mono.just(dnaSequence)
                .flatMap(this::validateDnaSequence)
                .map(strings -> strings.stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()))
                .map(OperationsMutantDna::evaluateMutantDna)
                .flatMap(mutantDnaRepository::saveRecord)
                .map(MutantDna::getIsMutant);
    }

    private Mono<List<String>> validateDnaSequence(List<String> dnaSequence) {
        return containsNotAllowedNitrogenousBases(dnaSequence)
                ? validateDnaSequenceStructure(dnaSequence)
                : Mono.error(MutantDnaException.Type.NOT_ALLOWED_NITROGENOUS_BASE::build);
    }

    private Mono<List<String>> validateDnaSequenceStructure(List<String> dnaSequence) {
        Boolean isValidStructure = validateStructure(dnaSequence);
        return isValidStructure ? Mono.just(dnaSequence) : Mono.error(MutantDnaException.Type.INVALID_STRUCTURE::build);
    }
}

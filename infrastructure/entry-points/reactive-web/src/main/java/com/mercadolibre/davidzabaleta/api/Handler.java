package com.mercadolibre.davidzabaleta.api;

import com.mercadolibre.davidzabaleta.api.dtos.DnaStatsDTO;
import com.mercadolibre.davidzabaleta.api.dtos.MutantDnaDTO;
import com.mercadolibre.davidzabaleta.usecase.mutant.MutantDnaUseCase;
import com.mercadolibre.davidzabaleta.usecase.stats.DnaStatsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final MutantDnaUseCase mutantDnaUseCase;
    private final DnaStatsUseCase dnaStatsUseCase;

    public Mono<ServerResponse> saveDnaSequence(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MutantDnaDTO.class)
                .flatMap(mutantDnaDTO -> mutantDnaUseCase.isMutant(mutantDnaDTO.getDna()))
                .flatMap(isMutant -> isMutant
                        ? ServerResponse.status(HttpStatus.OK).build()
                        : ServerResponse.status(HttpStatus.FORBIDDEN).build()
                );
    }

    public Mono<ServerResponse> getDnaStats(ServerRequest serverRequest) {
        return dnaStatsUseCase.getDnaStats()
                .map(DnaStatsDTO::domainToDto)
                .flatMap(dnaStatsDto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dnaStatsDto));
    }
}

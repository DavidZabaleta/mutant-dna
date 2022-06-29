package com.mercadolibre.davidzabaleta.api;

import com.mercadolibre.davidzabaleta.api.dtos.MutantDnaDTO;
import com.mercadolibre.davidzabaleta.usecase.mutant.MutantDnaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final MutantDnaUseCase mutantDnaUseCase;
//private  final UseCase2 useCase2;
//    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
//        // usecase.logic();
//        return ServerResponse.ok().bodyValue("");
//    }
//
//    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
//        // useCase2.logic();
//        return ServerResponse.ok().bodyValue("");
//    }

    public Mono<ServerResponse> saveDnaSequence(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MutantDnaDTO.class)
                .flatMap(mutantDnaDTO -> mutantDnaUseCase.isMutant(mutantDnaDTO.getDna()))
                .flatMap(isMutant -> isMutant
                        ? ServerResponse.status(HttpStatus.OK).build()
                        : ServerResponse.status(HttpStatus.FORBIDDEN).build()
                );
    }
}

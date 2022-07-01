package com.mercadolibre.davidzabaleta.usecase.stats;

import com.mercadolibre.davidzabaleta.model.DnaStats;
import com.mercadolibre.davidzabaleta.model.MutantDna;
import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DnaStatsUseCaseTest {

    @Mock
    private MutantDnaRepository mutantDnaRepository;

    @InjectMocks
    private DnaStatsUseCase dnaStatsUseCase;

    private final List<String> DNA_SEQUENCE_LIST = Arrays.asList("ACGT", "AAAA");

    @Test
    public void testGetDnaStats() {
        MutantDna mutantDna = MutantDna.builder()
                .dnaSequence(DNA_SEQUENCE_LIST)
                .isMutant(Boolean.FALSE)
                .build();
        DnaStats expected = DnaStats.builder()
                .countMutantDna(0)
                .countHumanDna(1)
                .ratio(0.0)
                .build();

        when(mutantDnaRepository.getAllDnaResults())
                .thenReturn(Flux.just(mutantDna));

        StepVerifier.create(dnaStatsUseCase.getDnaStats())
                .expectNext(expected)
                .verifyComplete();
    }
}
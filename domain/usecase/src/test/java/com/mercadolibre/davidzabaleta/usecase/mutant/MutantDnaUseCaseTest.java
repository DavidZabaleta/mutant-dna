package com.mercadolibre.davidzabaleta.usecase.mutant;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import com.mercadolibre.davidzabaleta.model.exceptions.MutantDnaException;
import com.mercadolibre.davidzabaleta.model.gateways.MutantDnaRepository;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(JUnitParamsRunner.class)
public class MutantDnaUseCaseTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MutantDnaRepository mutantDnaRepository;

    @InjectMocks
    private MutantDnaUseCase mutantDnaUseCase;

    @Test
    public void testIsMutantWhenSequenceIsValid() {
        List<String> dnaSequence = Arrays.asList("GTGCGA", "GAGAAC", "GTGTGT", "GGTAGG", "CTGTTA", "CCCCTG");

        MutantDna mutantDna = MutantDna.builder()
                .dnaSequence(Collections.emptyList())
                .isMutant(Boolean.TRUE)
                .build();

        Mockito.when(mutantDnaRepository.saveRecord(any()))
                .thenReturn(Mono.just(mutantDna));

        StepVerifier.create(mutantDnaUseCase.isMutant(dnaSequence))
                .expectNext(Boolean.TRUE)
                .verifyComplete();
    }

    @Test
    @Parameters(method = "dnaSequencesInvalidStructure")
    public void testIsMutantWhenInvalidStructure(List<String> dnaSequence, MutantDnaException exception) {
        StepVerifier.create(mutantDnaUseCase.isMutant(dnaSequence))
                .expectErrorMessage(exception.getMessage())
                .verify();
    }

    private Object[] dnaSequencesInvalidStructure() {
        return new Object[][]{
                {Arrays.asList("ATGCA", "CAAAAC", "TTATGT", "AGAGG", "CCCA", "TCACTG"), MutantDnaException.Type.INVALID_STRUCTURE.build()},
                {Arrays.asList("ATYCG", "CAGAA", "TTGHA", "AGGAA", "CCACA"), MutantDnaException.Type.NOT_ALLOWED_NITROGENOUS_BASE.build()}
        };
    }
}

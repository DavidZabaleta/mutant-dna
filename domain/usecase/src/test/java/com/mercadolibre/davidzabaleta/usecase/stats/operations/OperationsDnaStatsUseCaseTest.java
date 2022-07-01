package com.mercadolibre.davidzabaleta.usecase.stats.operations;

import com.mercadolibre.davidzabaleta.model.DnaStats;
import com.mercadolibre.davidzabaleta.model.MutantDna;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class OperationsDnaStatsUseCaseTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    @Parameters(method = "dnaStats")
    public void testCalculateDnaStats(int countMutantDna, int countHumanDna, double ratio, List<MutantDna> mutantDnaList) {
        DnaStats expected = DnaStats.builder()
                .countMutantDna(countMutantDna)
                .countHumanDna(countHumanDna)
                .ratio(ratio)
                .build();

        DnaStats result = OperationsDnaStatsUseCase.calculateDnaStats(mutantDnaList);

        Assertions.assertEquals(expected, result);
    }

    private Object[] dnaStats() {
        return new Object[][]{
                {1, 2, 0.5, Arrays.asList(MutantDna.builder().isMutant(Boolean.TRUE).build(),
                        MutantDna.builder().isMutant(Boolean.FALSE).build(),
                        MutantDna.builder().isMutant(Boolean.FALSE).build())},
                {0, 1, 0.0, List.of(MutantDna.builder().isMutant(Boolean.FALSE).build())},
                {0, 0, 0.0, Collections.emptyList()},
                {1, 0, 0.0, List.of(MutantDna.builder().isMutant(Boolean.TRUE).build())}
        };
    }

}
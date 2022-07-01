package com.mercadolibre.davidzabaleta.usecase.stats.operations;

import com.mercadolibre.davidzabaleta.model.DnaStats;
import com.mercadolibre.davidzabaleta.model.MutantDna;

import java.util.List;

public class OperationsDnaStatsUseCase {

    public OperationsDnaStatsUseCase() {}

    public static DnaStats calculateDnaStats(List<MutantDna> mutantDnaList) {
        int countMutantDna = calculateCountDnaByFlag(mutantDnaList, Boolean.TRUE);
        int countHumanDna = calculateCountDnaByFlag(mutantDnaList, Boolean.FALSE);

        return DnaStats.builder()
                .countMutantDna(countMutantDna)
                .countHumanDna(countHumanDna)
                .ratio(calculateRatio(countMutantDna, countHumanDna))
                .build();
    }

    private static int calculateCountDnaByFlag(List<MutantDna> mutantDnaList, Boolean isMutantCount) {
        return mutantDnaList.stream()
                .filter(mutantDna -> mutantDna.getIsMutant().equals(isMutantCount))
                .toList()
                .size();
    }

    private static double calculateRatio(int countMutantDna, int countHumanDna) {
        return (countHumanDna == 0) ? 0.0 : (double) countMutantDna / countHumanDna;
    }
}

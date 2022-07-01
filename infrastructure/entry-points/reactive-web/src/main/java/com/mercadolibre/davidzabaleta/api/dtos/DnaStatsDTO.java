package com.mercadolibre.davidzabaleta.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.davidzabaleta.model.DnaStats;
import lombok.Builder;

@Builder
public class DnaStatsDTO {

    @JsonProperty("count_mutant_dna")
    private int countMutantDna;

    @JsonProperty("count_human_dna")
    private int countHumanDna;

    @JsonProperty("ratio")
    private double ratio;

    public static DnaStatsDTO domainToDto(DnaStats dnaStats) {
        return DnaStatsDTO.builder()
                .countMutantDna(dnaStats.getCountMutantDna())
                .countHumanDna(dnaStats.getCountHumanDna())
                .ratio(dnaStats.getRatio())
                .build();
    }
}

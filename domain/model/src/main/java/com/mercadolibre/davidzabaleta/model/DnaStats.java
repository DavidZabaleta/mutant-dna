package com.mercadolibre.davidzabaleta.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DnaStats {
    private int countMutantDna;
    private int countHumanDna;
    private double ratio;
}

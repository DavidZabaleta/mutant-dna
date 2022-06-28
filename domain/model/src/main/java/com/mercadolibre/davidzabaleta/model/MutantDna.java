package com.mercadolibre.davidzabaleta.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MutantDna {
    private List<String> dnaSequence;
    private Boolean isMutant;
}

package com.mercadolibre.davidzabaleta.mongo.mutant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "mutant-dna")
public class MutantDnaData {

    @Id
    private final String idDna;
    private final String dnaSequence;
    private final Boolean isMutant;
}

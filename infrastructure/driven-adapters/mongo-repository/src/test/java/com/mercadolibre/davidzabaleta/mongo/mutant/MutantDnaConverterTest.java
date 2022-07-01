package com.mercadolibre.davidzabaleta.mongo.mutant;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class MutantDnaConverterTest {

    private final List<String> DNA_SEQUENCE_LIST = Arrays.asList("ACGT", "AAAA");

    @Test
    public void testConverters() {
        String id = "id";
        String dnaSequence = "ACGT AAAA";
        MutantDnaData mutantDnaData = new MutantDnaData(id, dnaSequence, Boolean.FALSE);
        MutantDna mutantDna = MutantDna.builder()
                .dnaSequence(DNA_SEQUENCE_LIST)
                .isMutant(Boolean.FALSE)
                .build();

        MutantDna resultConvertDataToModel = MutantDnaConverter.convertDataToModel(mutantDnaData);
        MutantDnaData resultConvertModelToData = MutantDnaConverter.convertModelToData(mutantDna);

        Assertions.assertEquals(resultConvertDataToModel, mutantDna);
        Assertions.assertEquals(resultConvertModelToData.getDnaSequence(), mutantDnaData.getDnaSequence());
        Assertions.assertEquals(resultConvertModelToData.getIsMutant(), mutantDnaData.getIsMutant());
    }
}
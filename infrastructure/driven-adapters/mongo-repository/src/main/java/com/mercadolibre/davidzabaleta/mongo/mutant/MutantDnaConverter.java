package com.mercadolibre.davidzabaleta.mongo.mutant;

import com.mercadolibre.davidzabaleta.model.MutantDna;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MutantDnaConverter {

    public static MutantDna convertDataToModel(MutantDnaData mutantDnaData) {
        return MutantDna.builder()
                .dnaSequence(convertDnaStringToList(mutantDnaData.getDnaSequence()))
                .isMutant(mutantDnaData.getIsMutant())
                .build();
    }

    public static MutantDnaData convertModelToData(MutantDna mutantDna) {
        return new MutantDnaData(
                UUID.randomUUID().toString(),
                convertDnaListToString(mutantDna.getDnaSequence()),
                mutantDna.getIsMutant()
        );
    }

    private static String convertDnaListToString(List<String> dnaSequence) {
        return String.join(" ", dnaSequence);
    }

    private static List<String> convertDnaStringToList(String dnaSequence) {
        return Arrays.stream(dnaSequence.split("[\\s]")).collect(Collectors.toList());
    }
}

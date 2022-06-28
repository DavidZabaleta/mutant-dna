package com.mercadolibre.davidzabaleta.usecase.mutant.operations;

import com.mercadolibre.davidzabaleta.model.MutantDna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OperationsMutantDna {
    private static final String NOT_ALLOWED_NITROGENOUS_BASE_REGEX = "[atcgATCG]+";

    public static MutantDna evaluateMutantDna(List<String> dnaSequence) {
        int totalOfHorizontalMutantGenes;
        int totalOfVerticalMutantGenes = 0;
        int totalOfDiagonalMutantGenes = 0;

        totalOfHorizontalMutantGenes = evaluateConsecutiveCharacters(dnaSequence);

        if (totalOfHorizontalMutantGenes <= 1)
            totalOfVerticalMutantGenes = evaluateVerticalMutantGenes(dnaSequence);

        if ((totalOfHorizontalMutantGenes + totalOfVerticalMutantGenes) <= 1)
            totalOfDiagonalMutantGenes = evaluateDiagonalMutantGenes(dnaSequence);

        int totalOfMutantGenes = totalOfHorizontalMutantGenes + totalOfVerticalMutantGenes + totalOfDiagonalMutantGenes;

        return MutantDna.builder()
                .dnaSequence(dnaSequence)
                .isMutant(totalOfMutantGenes > 1)
                .build();
    }

    private static Integer evaluateVerticalMutantGenes(List<String> dnaSequence) {
        List<String> dnaSequenceInColumns =  convertDnaSequenceToColumns(dnaSequence);

        return evaluateConsecutiveCharacters(dnaSequenceInColumns);
    }

    private static List<String> convertDnaSequenceToColumns(List<String> dnaSequence) {
        List<String> dnaSequenceColumns = new ArrayList<>(Collections.emptyList());

        for (int count = 0; count < dnaSequence.size(); count++) {
            StringBuilder newSeq = new StringBuilder();
            int mutableCount = count;

            dnaSequence.forEach(seq -> newSeq.append(seq.charAt(mutableCount)));

            dnaSequenceColumns.add(newSeq.toString());
        }

        return dnaSequenceColumns;
    }

    private static Integer evaluateDiagonalMutantGenes(List<String> dnaSequence) {
        List<String> dnaSequenceDiagonals = convertDnaSequenceToDiagonals(dnaSequence);

        return evaluateConsecutiveCharacters(dnaSequenceDiagonals);
    }

    private static List<String> convertDnaSequenceToDiagonals(List<String> dnaSequence) {
        List<String> dnaSequenceSuperiorDiagonals = new ArrayList<>(Collections.emptyList());
        List<String> dnaSequenceInferiorDiagonals = new ArrayList<>(Collections.emptyList());

        for (int i = 0; i < dnaSequence.size(); i++) {
            StringBuilder newSeqSuperior = new StringBuilder();

            for (int j = 0; j < dnaSequence.size() - i; j++) {
                newSeqSuperior.append(dnaSequence.get(j).charAt(j + i));
            }

            dnaSequenceSuperiorDiagonals.add(newSeqSuperior.toString());
        }

        for (int i = 0; i < dnaSequence.size(); i++) {
            StringBuilder newSeqInferior = new StringBuilder();

            for (int j = dnaSequence.size() - 1; j - i >= 0; j--) {
                newSeqInferior.append(dnaSequence.get(j).charAt(j - i));
            }

            dnaSequenceInferiorDiagonals.add(newSeqInferior.toString());
        }

        return Stream.concat(dnaSequenceSuperiorDiagonals.stream(), dnaSequenceInferiorDiagonals.stream())
                .filter(s -> s.length() >= 4)
                .collect(Collectors.toList());
    }

    private static Integer evaluateConsecutiveCharacters(List<String> dnaSequence) {
        int countMutantGenes = 0;

        for (String seq : dnaSequence) {
            int countConsecutive = 0;

            for (int i = 0; i < seq.length() - 1; i++) {
                if (seq.charAt(i) == seq.charAt(i + 1)) {
                    countConsecutive++;
                } else {
                    countConsecutive = 0;
                }

                if (countConsecutive == 3) {
                    countMutantGenes++;
                }
            }
        }

        return countMutantGenes;
    }

    public static Boolean containsNotAllowedNitrogenousBases(List<String> dnaSequence) {
        return dnaSequence.stream()
                .allMatch(seq -> seq.matches(NOT_ALLOWED_NITROGENOUS_BASE_REGEX));
    }

    public static Boolean validateStructure(List<String> dnaSequence) {
        int dnaSequenceSize = dnaSequence.size();
        return dnaSequence.stream()
                .filter(seq -> seq.length() == dnaSequenceSize)
                .count() == dnaSequenceSize;
    }
}

package com.mercadolibre.davidzabaleta.usecase.mutant.operations;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class OperationsMutantDnaTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    @Parameters(method = "dnaSequencesAreMutants")
    public void testIsMutantWhenSequenceIsGiven(List<String> dnaSequence, Boolean isMutant) {
        MutantDna expected = MutantDna.builder()
                .dnaSequence(dnaSequence)
                .isMutant(isMutant)
                .build();

        MutantDna result = OperationsMutantDna.evaluateMutantDna(dnaSequence);

        assertEquals(expected, result);
    }

    @Test
    public void testValidateStructureWhenIsGood() {
        List<String> dnaSequence = Arrays.asList("ATGCGA", "CAAAAC", "TTATGT", "AGATGG", "CCCCTA", "TCACTG");

        Boolean result = OperationsMutantDna.validateStructure(dnaSequence);

        assertTrue(result);
    }

    @Test
    public void testValidateStructureWhenIsBad() {
        List<String> dnaSequence = Arrays.asList("ATGA", "CAAAAC", "TTATGT", "AGATGG", "CCCCTA", "TCACTG");

        Boolean result = OperationsMutantDna.validateStructure(dnaSequence);

        assertFalse(result);
    }

    @Test
    public void testContainsNotAllowedNitrogenousBasesWhenIsGood() {
        List<String> dnaSequence = Arrays.asList("ATGCGA", "CAACAC", "TTATGT", "AGATGG", "CCCCTA", "TCACTG");

        Boolean result = OperationsMutantDna.containsNotAllowedNitrogenousBases(dnaSequence);

        assertTrue(result);
    }

    @Test
    public void testContainsNotAllowedNitrogenousBasesWhenIsBad() {
        List<String> dnaSequence = Arrays.asList("ATGCGA", "CAAHAC", "TTATYT", "AGATUG", "CCCCTA", "TCACTG");

        Boolean result = OperationsMutantDna.containsNotAllowedNitrogenousBases(dnaSequence);

        assertFalse(result);
    }

    private Object[] dnaSequencesAreMutants() {
        return new Object[][]{
                {Arrays.asList("ATGCGA", "CAAAAC", "TTATGT", "AGATGG", "CCCCTA", "TCACTG"), true}, // 2 horizontals AAAA, CCCC
                {Arrays.asList("ATGCG", "CAGAA", "TTGTA", "AGGAA", "CCACA"), true}, // 2 verticals GGGG, AAAA
                {Arrays.asList("ATGCGAT", "CAGAACT", "TGATGTA", "AGTATGC", "CACTTAT", "TCACTGA", "TAGCAGC"), true}, // 2 diagonals AAAA, 1 diagonal TTTT
                {Arrays.asList("ATGC", "AAAA", "ATAT", "AGAA"), true}, // 1 horizontal, 1 vertical AAAA, AAAA
                {Arrays.asList("GTGCGA", "GAGAAC", "GTGTGT", "GGTAGG", "CTGTTA", "TCACTG"), true}, // 1 vertical, 1 diagonal GGGG, TTTT
                {Arrays.asList("GTGCGA", "GAGAAC", "GTGTGT", "GGTAGG", "CTGTTA", "CCCCTG"), true}, // 1 vertical, 1 diagonal, 1 horizontal GGGG, TTTT, CCCC
                {Arrays.asList("ATGCGA", "CAACAC", "TTATGT", "AGATGG", "CGCCTA", "TCACTG"), false},
                {Arrays.asList("ATGCG", "CGGAT", "TTATA", "AGGAA", "CCACA"), false},
                {Arrays.asList("ATGC", "GTAA", "ATAT", "AGAA"), false}
        };
    }
}
package com.mercadolibre.davidzabaleta.mongo.mutant;

import com.mercadolibre.davidzabaleta.model.MutantDna;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutantDnaRepositoryAdapterTest {

    @Mock
    private MutantDnaDataRepository mutantDnaDataRepository;

    @InjectMocks
    private MutantDnaRepositoryAdapter mutantDnaRepositoryAdapter;

    private final String DNA_SEQUENCE = "ACGT AAAA";
    private final List<String> DNA_SEQUENCE_LIST = Arrays.asList("ACGT", "AAAA");
    private final String ID = "id";

    @Test
    public void testSaveRecord() {
        MutantDnaData mutantDnaData = new MutantDnaData(ID, DNA_SEQUENCE, Boolean.FALSE);
        MutantDna mutantDna = MutantDna.builder()
                .dnaSequence(DNA_SEQUENCE_LIST)
                .isMutant(Boolean.FALSE)
                .build();

        when(mutantDnaDataRepository.save(any()))
                .thenReturn(Mono.just(mutantDnaData));

        StepVerifier.create(mutantDnaRepositoryAdapter.saveRecord(mutantDna))
                .expectNext(mutantDna)
                .verifyComplete();
    }

    @Test
    public void testGetAllDnaResults() {
        MutantDnaData mutantDnaData = new MutantDnaData(ID, DNA_SEQUENCE, Boolean.FALSE);
        MutantDna mutantDna = MutantDna.builder()
                .dnaSequence(DNA_SEQUENCE_LIST)
                .isMutant(Boolean.FALSE)
                .build();

        when(mutantDnaDataRepository.findAll())
                .thenReturn(Flux.just(mutantDnaData));

        StepVerifier.create(mutantDnaRepositoryAdapter.getAllDnaResults())
                .expectNext(mutantDna)
                .verifyComplete();
    }

}
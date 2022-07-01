# Test de ADN Mutante

## Introducción
Este proyecto consiste en una REST API que evalua si una cadena de ADN dada corresponde a una cadena mutante o humana.

## Stack

- Spring Webflux
- Java 17
- MongoDB
- Docker
- AWS ECS

## Servicios

__POST - /mutant__

    curl --location --request POST 'https://75r4vd5ajg.execute-api.us-east-1.amazonaws.com/adn-test/mutant' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }'


Respuesta:
- HTTP 200-OK: La cadena ingresada es de ADN humano
- HTTP 403-Forbidden: La cadena ingresada es de ADN mutante

__GET - /stats__

    curl --location --request GET 'https://75r4vd5ajg.execute-api.us-east-1.amazonaws.com/adn-test/stats'

Respuesta:
- HTTP 200-OK:

    ``{"count_mutant_dna":11,"count_human_dna":12,"ratio":0.9166666666666666}``

## Diccionario de Errores

- __NOT_ALLOWED_NITROGENOUS_BASE__: Una de las bases nitrogenadas en la cadena no corresponde con las permitidas (A,T,C,G)

- __INVALID_STRUCTURE__: La estructura del ADN no cumple con los requisitos de NxN


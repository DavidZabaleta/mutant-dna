#!/bin/bash
FROM amazoncorretto:17.0.3

COPY applications/app-service/build/libs/mutants-test.jar /mutant-test.jar

CMD ["java", "-jar", "/mutant-test.jar"]

package com.mercadolibre.davidzabaleta.model.exceptions;

public class MutantDnaException extends RuntimeException {
    public enum Type {
        NOT_ALLOWED_NITROGENOUS_BASE("Una de las bases nitrogenadas en la cadena no corresponde con las permitidas (A,T,C,G)"),
        INVALID_STRUCTURE("La estructura del ADN no cumple con los requisitos de NxN");

        private String message;

        public MutantDnaException build() {
            return new MutantDnaException(this);
        }

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private final Type type;

    private MutantDnaException(Type type) {
        super(type.message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}

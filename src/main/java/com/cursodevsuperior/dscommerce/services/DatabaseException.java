package com.cursodevsuperior.dscommerce.services;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String falhaDeIntegridadeReferencial) {
     super(falhaDeIntegridadeReferencial);
    }
}

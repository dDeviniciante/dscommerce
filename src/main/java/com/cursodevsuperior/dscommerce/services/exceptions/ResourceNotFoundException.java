package com.cursodevsuperior.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException  { // runtimeexception n exige try catch

    public ResourceNotFoundException(String message) {
            super(message);
    }
}

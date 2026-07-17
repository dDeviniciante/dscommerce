package com.cursodevsuperior.dscommerce.dto;

public class FIeldMessage {

    private String fieldName;
    private String message;

    public FIeldMessage(String message, String fieldName) {
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }


}

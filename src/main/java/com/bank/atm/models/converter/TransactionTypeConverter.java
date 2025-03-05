package com.bank.atm.models.converter;

import com.bank.atm.models.TransactionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {


    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        return transactionType.getValue();
    }

    @Override
    public TransactionType convertToEntityAttribute(String s) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.getValue().equalsIgnoreCase(s)) {
                return transactionType;
            }
        }
        return null;
    }
}

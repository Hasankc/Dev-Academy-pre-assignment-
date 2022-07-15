package com.example.preassignmentdev;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CustomLocalDateTimeConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        // TODO Auto-generated method stub
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(value, formatter);
        return ldt;
    }
    
}

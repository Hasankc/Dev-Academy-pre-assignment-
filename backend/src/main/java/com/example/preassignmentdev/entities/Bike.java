package com.example.preassignmentdev.entities;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import com.example.preassignmentdev.CustomLocalDateTimeConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Data
public class Bike {

    @Id
    @GeneratedValue
    private long id;

    @CsvCustomBindByPosition(position = 0, converter = CustomLocalDateTimeConverter.class)
    private LocalDateTime departureDate;

    @CsvCustomBindByPosition(position = 1, converter = CustomLocalDateTimeConverter.class)
    private LocalDateTime returnDate;

    @CsvBindByPosition(position = 2)
    private Integer departureStationId;

    @CsvBindByPosition(position = 3)
    private String departureStationName;

    @CsvBindByPosition(position = 4)
    private Integer returnStationId;

    @CsvBindByPosition(position = 5)
    private String returnStationName;

    @CsvBindByPosition(position = 6)
    private Double coveredDistance;

    @CsvBindByPosition(position = 7)
    private Integer duration;
}
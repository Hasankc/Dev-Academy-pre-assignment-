package com.example.preassignmentdev;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import com.opencsv.bean.CsvToBeanFilter;
public class TimeAndDistanceFilter implements CsvToBeanFilter  {

    @Override
    public boolean allowLine(String[] line) {
        String departureString = line[0];
        String returnString = line[1];
        String coveredDistanceStr = line[6];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime departureLdt = LocalDateTime.parse(departureString, formatter);
        LocalDateTime returnLdt = LocalDateTime.parse(returnString, formatter);
        double coveredDistance = Double.parseDouble(coveredDistanceStr);

        Long departureSeconds = departureLdt.toEpochSecond(ZoneOffset.UTC);
        Long returnSeconds = returnLdt.toEpochSecond(ZoneOffset.UTC);
        Long tripDuration = returnSeconds - departureSeconds;
       
        if (tripDuration >= 10 && coveredDistance >= 10) {
            return true;
        } else {
            return false;
        }
    }
}
    

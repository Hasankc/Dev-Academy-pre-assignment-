package com.example.preassignmentdev;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.preassignmentdev.entities.Bike;
import com.example.preassignmentdev.repository.BikeRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvProcessRunner implements CommandLineRunner {
    // Dependency Injection of BikeRepository
    @Autowired 
    private BikeRepository bikeRepository;

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(CsvProcessRunner.class);

    // Inside run() method I wrote code that reads the CSV and puts data to database
    @Override
    public void run (String... args) throws Exception {
        logger.info("Hello I will start reading csv files");
        try {
            // load csv data
            Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("bikes.csv").toURI()));
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withSkipLines(1)
                        .withCSVParser(parser)
                        .build();
            // map csv to java object
            List<Bike> bikes = new CsvToBeanBuilder(csvReader)
                        .withFilter(new TimeAndDistanceFilter())
                        .withType(Bike.class)
                        .build()
                        .parse();

            logger.info("bikes.csv is read, now I will put data to DB");
            logger.info("Bike data record count: {}", bikes.size());
            // save entities to database
            int batchSize = 100;
            // int limit = bikes.size();
            int limit = 1000;
            int startIndex = 0;
            int lastIndex = batchSize;

            while(lastIndex <= limit) {
                if (lastIndex == limit) {
                    break;
                }
                List<Bike> subList = bikes.subList(startIndex, lastIndex);
                bikeRepository.saveAllAndFlush(subList);
                logger.info("Processed {} bike rows", lastIndex);
                if ((lastIndex + batchSize) <= limit) {
                    startIndex = lastIndex;
                    lastIndex += batchSize;
                } else {
                    lastIndex = limit;
                }
            }
        } catch (Exception e) {
            logger.error("Sorry there was a problem reading the CSV file.", e);
        }
        }
}
package com.example.preassignmentdev.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.preassignmentdev.entities.Bike;

@Repository // for getting and saving data to database
public interface BikeRepository extends JpaRepository<Bike, Long> {
    
}

package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> findSchedulesByPetId(@Param("petId") long petId);

    @Query("SELECT s FROM Schedule s JOIN s.employees e WHERE e.id = :employeeId")
    List<Schedule> findSchedulesByEmployeeId(@Param("employeeId") long employeeId);

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.owner.id = :customerId")
    List<Schedule> findSchedulesByCustomerId(@Param("customerId") long customerId);
}
package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findSchedulesByPetId(long petId) {
        return scheduleRepository.findSchedulesByPetId(petId);
    }

    public List<Schedule> findSchedulesByEmployeeId(long employeeId) {
        return scheduleRepository.findSchedulesByEmployeeId(employeeId);
    }

    public List<Schedule> findSchedulesByCustomerId(long customerId) {
        return scheduleRepository.findSchedulesByCustomerId(customerId);
    }

}

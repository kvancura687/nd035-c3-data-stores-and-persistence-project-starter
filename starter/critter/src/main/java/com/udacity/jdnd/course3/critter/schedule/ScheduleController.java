package com.udacity.jdnd.course3.critter.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ScheduleService scheduleService;

    // Reusable ScheduleDTO mapper
    private ScheduleDTO mapToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        if (schedule != null) {
            scheduleDTO.setId(schedule.getId());
            scheduleDTO.setDate(schedule.getDate());
        }
        if (schedule != null && schedule.getEmployees() != null) {
            List<Long> employeeIds = new ArrayList<>();
            for (Employee employee : schedule.getEmployees())
                employeeIds.add(employee.getId());
            scheduleDTO.setEmployeeIds(employeeIds);
        }
        if (schedule != null && schedule.getPets() != null) {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : schedule.getPets())
                petIds.add(pet.getId());
            scheduleDTO.setPetIds(petIds);
        }
        if (schedule != null && schedule.getActivities() != null) {
            Set<EmployeeSkill> activities = new HashSet<>();
            for (EmployeeSkill skill : schedule.getActivities())
                activities.add(skill);
            scheduleDTO.setActivities(activities);
        }
        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        List<Employee> employees = employeeService.findByEmployeeIdIn(scheduleDTO.getEmployeeIds());
        List<Pet> pets = petService.findPetsByIds(scheduleDTO.getPetIds());
        schedule.setEmployees(new HashSet<>(employees));
        schedule.setPets(new HashSet<>(pets));

        Schedule savedSchedule = scheduleService.save(schedule);
        return mapToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAllSchedules();
        return schedules.stream()
                .map(this::mapToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.findSchedulesByPetId(petId);
        return schedules.stream()
                .map(this::mapToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.findSchedulesByEmployeeId(employeeId);
        return schedules.stream()
                .map(this::mapToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.findSchedulesByCustomerId(customerId);
        return schedules.stream()
                .map(this::mapToScheduleDTO)
                .collect(Collectors.toList());
    }
}

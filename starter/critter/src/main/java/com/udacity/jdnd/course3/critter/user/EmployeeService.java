package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployeesByIds(List<Long> employeeIds) {
        return employeeRepository.findAllById(employeeIds);
    }

    public Employee findEmployee(long employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee ID " + employeeId + " not found."));
}

    public List<Employee> findByEmployeeIdIn(List<Long> employeeIds) {
        if (employeeIds == null || employeeIds.isEmpty()) {
            return Collections.emptyList();
        }
        return employeeRepository.findByIdIn(employeeIds);
    }

    public Employee save(Employee employee) {
    return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> findEmployeesForService(Set<DayOfWeek> daysAvailable, Set<EmployeeSkill> skills) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getDaysAvailable().containsAll(daysAvailable))
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
}
}

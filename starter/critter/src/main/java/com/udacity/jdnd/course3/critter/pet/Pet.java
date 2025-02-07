package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    private String name;

    @Enumerated
    private PetType type;

    private LocalDate birthDate;

    private String notes;

    @ManyToOne
    private Customer owner;

    @ManyToMany
    private Set<Schedule> schedule = new HashSet<>();

    public Pet() {}

    public Pet(String name, PetType type, LocalDate birthDate, String notes, Customer owner) {
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
    }

    public Pet(PetDTO petDTO, Customer owner) {
        this.name = petDTO.getName();
        this.type = petDTO.getType();
        this.birthDate = petDTO.getBirthDate();
        this.notes = petDTO.getNotes();
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getCustomer() {
        return owner;
    }

    public void setCustomer(Customer owner) {
        this.owner = owner;
    }

    public Set<Schedule> getSchedules() {
        return schedule;
    }
    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }
}

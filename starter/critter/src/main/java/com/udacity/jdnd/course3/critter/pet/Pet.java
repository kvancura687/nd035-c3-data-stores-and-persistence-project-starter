package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets_tab")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    private Long id;

    @Nationalized
    @Column(name = "pet_name")
    private String name;

    @Enumerated
    @Column(name = "pet_type")
    private PetType type;

    @Column(name = "pet_birthDate")
    private LocalDate birthDate;

    @Column(name = "pet_notes", length=2500)
    private String notes;

    @ManyToOne
    @Column(name = "pet_owner")
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

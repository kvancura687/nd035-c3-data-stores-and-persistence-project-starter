package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long>{
    List<Pet> findByOwnerId(Long ownerId);
}
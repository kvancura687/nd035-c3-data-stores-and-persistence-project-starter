package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Optional<Pet> findPetById(long petId) {
        return petRepository.findById(petId);
    }

    public List<Pet> findPetsByIds(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }

    public List<Pet> findPetsByOwnerId(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Pet save(Pet pet, Customer owner) {
        pet = petRepository.save(pet);
        owner.addPet(pet);
        customerRepository.save(owner);
        return pet;
    }

}
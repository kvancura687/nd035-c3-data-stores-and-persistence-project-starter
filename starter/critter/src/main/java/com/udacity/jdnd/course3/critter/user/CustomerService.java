package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Optional<Customer> findCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByPetId(Long petId) {
    return petRepository.findById(petId)
            .map(Pet::getCustomer)
            .orElseThrow(() -> new EntityNotFoundException("Pet ID " + petId + " not found."));
    }

    public Customer findCustomerById(long ownerId) {
        return customerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer ID " + ownerId + " not found."));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
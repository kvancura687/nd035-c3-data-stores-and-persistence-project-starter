package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;

import jakarta.persistence.EntityNotFoundException;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    // Reusable PetDTO mapper
    private PetDTO mapToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if (pet.getCustomer() != null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }
        return petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer owner = customerService.findCustomerById(petDTO.getOwnerId());
        pet.setCustomer(owner);

        Pet savedPet = petService.save(pet, petDTO.getOwnerId());
        return mapToPetDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Optional<Pet> optionalPet = petService.findPetById(petId);
        Pet pet = optionalPet.orElseThrow(() -> 
            new EntityNotFoundException("Pet ID " + petId + " not found.")
        );
        return mapToPetDTO(pet);
}

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.findAllPets();
        return pets.stream()
                .map(this::mapToPetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwnerId(ownerId);
        return pets.stream()
                .map(this::mapToPetDTO)
                .collect(Collectors.toList());
    }
}

package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repository.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petsRepository;

    public PetSDJpaService(PetRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petsRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petsRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petsRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petsRepository.deleteById(aLong);
    }
}

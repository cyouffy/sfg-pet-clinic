package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repository.VetRpository;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    private final VetRpository vetRpository;

    public VetSDJpaService(VetRpository vetRpository) {
        this.vetRpository = vetRpository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRpository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRpository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRpository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRpository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRpository.deleteById(aLong);
    }
}

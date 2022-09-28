package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
}

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Smith");
        owner1.setAddress("123 Flower");
        owner1.setCity("Boston");
        owner1.setTelephone("123-123-1234");

        Pet johnDog = new Pet();
        johnDog.setName("Rouge");
        johnDog.setBirthDate(LocalDate.now());
        johnDog.setPetType(savedDogPetType);
        johnDog.setOwner(owner1);

        owner1.getPets().add(johnDog);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jake");
        owner2.setLastName("Robertson");
        owner2.setAddress("123 Flower");
        owner2.setCity("Boston");
        owner2.setTelephone("123-123-1234");

        Pet jakeCat = new Pet();
        jakeCat.setName("Spoke");
        jakeCat.setBirthDate(LocalDate.now());
        jakeCat.setPetType(savedCatPetType);
        jakeCat.setOwner(owner2);

        owner2.getPets().add(jakeCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(jakeCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Kale");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mort");
        vet2.setLastName("Monroe");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
    }

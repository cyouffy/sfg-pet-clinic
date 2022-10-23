package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId= 1L;
    final String ownerLastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(),new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1,ownerSet.size());
    }

    @Test
    void findById() {
        Owner ownerById = ownerServiceMap.findById(ownerId);

        assertEquals(ownerId, ownerById.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner newOwner = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(newOwner);
        assertNotNull(savedOwner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
       Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner ownerByLastName = ownerServiceMap.findByLastName(ownerLastName);
        assertNotNull(ownerByLastName);
        assertEquals(ownerId, ownerByLastName.getId());
    }

    @Test
    void findByLastNameNotExcsist() {
        Owner ownerByLastName = ownerServiceMap.findByLastName("foo");
        assertNull(ownerByLastName);
    }
}
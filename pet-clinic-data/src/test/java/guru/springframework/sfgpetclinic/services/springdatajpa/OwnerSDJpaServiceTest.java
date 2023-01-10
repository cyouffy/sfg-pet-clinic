package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).lastName(LAST_NAME).build());
        returnOwnersSet.add(Owner.builder().id(2L).lastName("Kelly").build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> allOwners = service.findAll();

        assertEquals(returnOwnersSet.size(), allOwners.size());
    }

    @Test
    void findById_NotNULL() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));

        Owner ownerById = service.findById(1L);

        assertNotNull(ownerById);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void findById_NULL() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        Owner ownerById = service.findById(1L);

        assertNull(ownerById);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(service.findByLastName(any())).thenReturn(returnOwner);
        Owner smith = ownerRepository.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}
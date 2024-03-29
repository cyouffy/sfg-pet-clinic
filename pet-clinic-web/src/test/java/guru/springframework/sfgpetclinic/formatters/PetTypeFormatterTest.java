package guru.springframework.sfgpetclinic.formatters;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PetTypeFormatterTest {
    private static final String PET_TYPE_DOG = "Dog";
    private static final String PET_TYPE_CAT = "Cat";
    private static final String PET_TYPE_FISH = "Fish";

    private PetTypeFormatter petTypeFormatter;

    private Set<PetType> petTypes;

    private PetTypeService petTypeService;

    @BeforeEach
    void setup() {
        petTypeService = Mockito.mock(PetTypeService.class);
        petTypeFormatter = new PetTypeFormatter(petTypeService);
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name(PET_TYPE_DOG).build());
        petTypes.add(PetType.builder().id(2L).name(PET_TYPE_CAT).build());
    }

    @Test
    void printTest() {
        // Given
        PetType petType = PetType.builder().name(PET_TYPE_DOG).build();

        // When
        String petTypeName = petTypeFormatter.print(petType, Locale.ENGLISH);

        // Then
        assertEquals(PET_TYPE_DOG, petTypeName);
    }

    @Test
    void parseTest() throws ParseException {
        // Given
        when(petTypeService.findAll()).thenReturn(petTypes);

        // When
        PetType petType = petTypeFormatter.parse(PET_TYPE_CAT, Locale.ENGLISH);

        // Then
        assertEquals(PET_TYPE_CAT, petType.getName());
    }

    @Test
    void parseThrowsExceptionTest() {
        Assertions.assertThrows(ParseException.class, () -> petTypeFormatter.parse(PET_TYPE_FISH, Locale.ENGLISH));
    }
}

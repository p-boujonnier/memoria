package fr.fae.memoria.charona.features.role.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role fullRole;
    private Role minimalRole;

    @BeforeEach
    void setUp() {
        fullRole = new Role.Builder()
                .code("ROLE_MJ")
                .label("Maître du Jeu")
                .description("Fonctionnalités MJ transversales")
                .assignable(true)
                .askable(true)
                .build();

        minimalRole = new Role.Builder()
                .code("ROLE_USER")
                .label("Utilisateur")
                .build();
    }

    @Test
    void builderShouldPopulateFields() {
        assertEquals("ROLE_MJ", fullRole.getCode());
        assertEquals("Maître du Jeu", fullRole.getLabel());
        assertEquals("Fonctionnalités MJ transversales", fullRole.getDescription());
        assertTrue(fullRole.isAssignable());
        assertTrue(fullRole.isAskable());
    }

    @Test
    void descriptionShouldBeNullWhenNotSet() {
        assertNull(minimalRole.getDescription());
    }

    @Test
    void assignableAndAskableShouldBeFalseByDefault() {
        assertFalse(minimalRole.isAssignable());
        assertFalse(minimalRole.isAskable());
    }

    @Test
    void creationDateShouldBeNullBeforePersistence() {
        assertNull(fullRole.getCreationDate());
        assertNull(minimalRole.getCreationDate());
    }

    @Test
    void idShouldBeZeroByBeforePersistence() {
        assertEquals(0, fullRole.getId());
        assertEquals(0, minimalRole.getId());
    }
}
package fr.fae.memoria.fabula.features.personage.infrastructure.persistence;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import fr.fae.memoria.fabula.features.personage.domain.repositories.IPersonageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Profile("mock")
@Repository
public class PersonageRepositoryMock implements IPersonageRepository {

    private final Map<UUID, Personage> personages = new HashMap<>();

    public PersonageRepositoryMock() {
        List.of(
                new Personage.Builder()
                        .firstName("Frodon")
                        .lastName("Sacquet")
                        .age(50)
                        .build(),
                new Personage.Builder()
                        .firstName("Samsagace")
                        .lastName("Gamegie")
                        .age(38)
                        .build(),
                new Personage.Builder()
                        .firstName("Meriadoc")
                        .lastName("Brandebouc")
                        .age(36)
                        .build(),
                new Personage.Builder()
                        .firstName("Peregrin")
                        .lastName("Touque")
                        .age(28)
                        .build(),
                new Personage.Builder()
                        .firstName("Gandalf")
                        .lastName("le Gris")
                        .age(2019)
                        .build(),
                new Personage.Builder()
                        .firstName("Aragorn")
                        .lastName("Grand-Pas")
                        .age(87)
                        .build(),
                new Personage.Builder()
                        .firstName("Boromir")
                        .lastName("du Gondor")
                        .age(41)
                        .build(),
                new Personage.Builder()
                        .firstName("Legolas")
                        .lastName("Vertefeuille")
                        .age(2931)
                        .build(),
                new Personage.Builder()
                        .firstName("Gimli")
                        .lastName("fils de Gloin")
                        .age(139)
                        .build()
        ).forEach(personage -> personages.put(UUID.randomUUID(), personage));
    }


    @Override
    public Personage save(Personage personage) {
        UUID id = personage.getId() != null ? personage.getId() : UUID.randomUUID();
        personages.put(id, personage);
        return personage;
    }

    @Override
    public Optional<Personage> findById(UUID id) {
        return Optional.ofNullable(personages.get(id));
    }

    @Override
    public List<Personage> findAll() {
        return List.copyOf(personages.values());
    }

    @Override
    public List<Personage> findByOwnerId(UUID ownerId) {
        return personages.values().stream()
                .filter(p -> ownerId.equals(p.getOwnerId()))
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        personages.remove(id);
    }
}

package fr.fae.memoria.fabula.features.personage.infrastructure.persistence;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import fr.fae.memoria.fabula.features.personage.domain.repositories.IPersonageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile({"jpa", "test", "dev"})
@Repository
public class PersonageRepositoryJpaAdapter implements IPersonageRepository {

    private final PersonageJpaRepository personageJpaRepository;

    public PersonageRepositoryJpaAdapter(PersonageJpaRepository personageJpaRepository) {
        this.personageJpaRepository = personageJpaRepository;
    }

    @Override
    public Personage save(Personage personage) {
        return personageJpaRepository.save(personage);
    }

    @Override
    public Optional<Personage> findById(UUID id) {
        return personageJpaRepository.findById(id);
    }

    @Override
    public List<Personage> findAll() {
        return personageJpaRepository.findAll();
    }

    @Override
    public List<Personage> findByOwnerId(UUID ownerId) {
        return personageJpaRepository.findByOwnerId(ownerId);
    }

    @Override
    public void deleteById(UUID id) {
        personageJpaRepository.deleteById(id);
    }
}

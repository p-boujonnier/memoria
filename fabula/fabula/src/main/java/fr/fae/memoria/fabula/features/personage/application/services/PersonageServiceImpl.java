package fr.fae.memoria.fabula.features.personage.application.services;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import fr.fae.memoria.fabula.features.personage.domain.repositories.IPersonageRepository;
import fr.fae.memoria.fabula.features.personage.domain.services.IPersonageService;
import fr.fae.memoria.fabula.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonageServiceImpl implements IPersonageService {

    private final IPersonageRepository personageRepository;

    public PersonageServiceImpl(IPersonageRepository personageRepository) {
        this.personageRepository = personageRepository;
    }

    @Override
    public ServiceResponse<Personage> create(Personage personage) {
        try {
            return new ServiceResponse<>("201", "Personnage créé avec succès", personageRepository.save(personage));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la création du personnage", null);
        }
    }

    @Override
    public ServiceResponse<Personage> update(UUID id, Personage entity) {
        try {
            Personage personage = personageRepository.findById(id).orElse(null);
            if (personage == null) {
                return new ServiceResponse<>("404", "Personnage introuvable", null);
            }

            personage.setFirstName(entity.getFirstName());
            personage.setLastName(entity.getLastName());
            personage.setAge(entity.getAge());

            return new ServiceResponse<>("200", "Personnage modifié avec succès", personageRepository.save(personage));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la modification du personnage", null);
        }
    }

    @Override
    public ServiceResponse<Personage> findById(UUID id) {
        try {
            return personageRepository.findById(id)
                    .map(personage -> new ServiceResponse<>("200", "Personnage trouvé", personage))
                    .orElse(new ServiceResponse<>("404", "Personnage introuvable", null));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la recherche du personnage", null);
        }
    }

    @Override
    public ServiceResponse<List<Personage>> findAll() {
        try {
            return new ServiceResponse<>("200", "Liste des personnages", personageRepository.findAll());
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la récupération de la liste des personnages", null);
        }
    }

    @Override
    public ServiceResponse<List<Personage>> findByOwnerId(UUID ownerId) {
        try {
            return new ServiceResponse<>("200", "Liste des personnages trouvés", personageRepository.findByOwnerId(ownerId));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la récupération de la liste des personnages", null);
        }
    }

    @Override
    public ServiceResponse<Boolean> delete(UUID id) {
        try {
            if (personageRepository.findById(id).isEmpty()) {
                return new ServiceResponse<>("404", "Personnage introuvable", false);
            }
            personageRepository.deleteById(id);
            return new ServiceResponse<>("200", "Personnage supprimé avec succès", true);
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la suppression du personnage", false);
        }
    }
}

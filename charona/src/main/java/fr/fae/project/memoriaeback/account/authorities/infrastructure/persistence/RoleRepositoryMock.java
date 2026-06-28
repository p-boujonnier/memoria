package fr.fae.project.memoriaeback.account.authorities.infrastructure.persistence;

import fr.fae.project.memoriaeback.account.authorities.domain.models.Role;
import fr.fae.project.memoriaeback.account.authorities.domain.ports.IRoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Profile("mock")
@Repository
public class RoleRepositoryMock implements IRoleRepository {

    private final Map<String, Role> store = new HashMap<>();

    public RoleRepositoryMock() {
        List.of(
                new Role.Builder()
                        .code("ROLE_USER")
                        .label("Utilisateur")
                        .description("Accès de base, Charona uniquement.")
                        .assignable(false)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_FABULA_USER")
                        .label("Fabula — Utilisateur")
                        .description("Accès à ses propres personnages et notes.")
                        .assignable(true)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_FABULA_ADMIN")
                        .label("Fabula — Administrateur")
                        .description("Accès à toutes les données Fabula.")
                        .assignable(true)
                        .askable(true)
                        .build(),
                new Role.Builder()
                        .code("ROLE_HEPHA_USER")
                        .label("Hépha — Utilisateur")
                        .description("Consultation du référentiel SRD.")
                        .assignable(true)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_HEPHA_ADMIN")
                        .label("Hépha — Administrateur")
                        .description("Modification et enrichissement du référentiel SRD.")
                        .assignable(true)
                        .askable(true)
                        .build(),
                new Role.Builder()
                        .code("ROLE_LIBRIA_USER")
                        .label("Libria — Utilisateur")
                        .description("Consultation des archives.")
                        .assignable(true)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_LIBRIA_ADMIN")
                        .label("Libria — Administrateur")
                        .description("Ajout et indexation de documents.")
                        .assignable(true)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_CHARONA_ADMIN")
                        .label("Charona — Administrateur")
                        .description("Gestion des comptes et des rôles.")
                        .assignable(true)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_MJ")
                        .label("Maître du Jeu")
                        .description("Fonctionnalités MJ transversales sur tous les modules.")
                        .assignable(true)
                        .askable(true)
                        .build(),
                new Role.Builder()
                        .code("ROLE_ADMIN")
                        .label("Administrateur")
                        .description("Accès total, gestion des utilisateurs et des rôles.")
                        .assignable(false)
                        .askable(false)
                        .build(),
                new Role.Builder()
                        .code("ROLE_DEV")
                        .label("Développeur")
                        .description("Accès au Sandbox.")
                        .assignable(false)
                        .askable(false)
                        .build()
                ).forEach(role -> store.put(role.getCode(), role));
        }


    @Override
    public List<Role> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public List<Role> findAllAskable() {
        return List.copyOf(store.values().stream().filter(Role::isAskable).toList());
    }

    @Override
    public List<Role> findAllAssignable() {
        return List.copyOf(store.values().stream().filter(Role::isAssignable).toList());
    }

    @Override
    public Optional<Role> findByCode(String code) {
        return Optional.ofNullable(store.get(code));
    }

    @Override
    public Role save(Role role) {
        store.put(role.getCode(), role);
        return role;
    }

    @Override
    public void deleteByCode(String code) {
        store.remove(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return store.containsKey(code);
    }
}

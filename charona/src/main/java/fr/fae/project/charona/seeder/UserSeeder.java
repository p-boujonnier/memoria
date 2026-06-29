package fr.fae.project.charona.seeder;

import fr.fae.project.charona.features.user.domain.services.IUserService;
import fr.fae.project.charona.features.user.domain.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class UserSeeder {
    IUserService userService;

    public UserSeeder(IUserService userService) {
        this.userService = userService;
    }

    public void seed() {
        if (userService.findAll().getData().isEmpty()) {
            userService.create(new User("Admin", "admin@memoriae.dev", "admin123"));
            userService.create(new User("Master", "master@memoriae.dev", "master123"));
            userService.create(new User("Player", "player@memoriae.dev", "player123"));
        }
    }
}



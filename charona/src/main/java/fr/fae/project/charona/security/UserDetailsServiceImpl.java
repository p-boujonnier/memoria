package fr.fae.project.charona.security;

import fr.fae.project.charona.features.user.domain.repositories.IUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        var entity = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));

        return new org.springframework.security.core.userdetails.User(
                entity.getId().toString(),
                entity.getPassword(),
                entity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getCode())).toList()
        );
    }
}

package fr.fae.memoria.charona.features.user.infrastructure.api.dtos.responses;

import java.util.List;
import java.util.UUID;

public class UserPublicResponse {

    private UUID id;
    private String pseudo;
    private String email;
    private List<String> roles;

    public UserPublicResponse() {}

    public UserPublicResponse(UUID id, String pseudo, String email, List<String> roles) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setRoles(roles);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
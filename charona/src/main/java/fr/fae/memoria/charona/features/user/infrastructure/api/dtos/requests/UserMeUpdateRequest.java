package fr.fae.memoria.charona.features.user.infrastructure.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserMeUpdateRequest {

    @NotBlank(message = "Veuillez renseigner un nouveau pseudo")
    @Size(min = 3, message = "Votre pseudo doit contenir au moins 3 caractères")
    private String pseudo;

    @NotBlank(message = "Veuillez renseigner un nouveau email")
    @Email(message = "Veuillez renseigner un email valide.")
    private String email;

    public UserMeUpdateRequest() {
    }

    public UserMeUpdateRequest(String pseudo, String email) {
        this.setPseudo(pseudo);
        this.setEmail(email);
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

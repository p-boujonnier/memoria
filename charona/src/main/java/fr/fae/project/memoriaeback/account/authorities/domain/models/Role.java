package fr.fae.project.memoriaeback.account.authorities.domain.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles", schema = "charona")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 50)
    private String label;

    private String description;

    @Column(nullable = false)
    private boolean assignable;

    @Column(nullable = false)
    private boolean askable;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    // Constructor
    public Role() {
    }

    // Persistence
    @PrePersist
    private void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAssignable() {
        return assignable;
    }

    public void setAssignable(boolean assignable) {
        this.assignable = assignable;
    }

    public boolean isAskable() {
        return askable;
    }

    public void setAskable(boolean askable) {
        this.askable = askable;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    // Builder
    Role(Builder builder) {
        this.code = builder.code;
        this.label = builder.label;
        this.description = builder.description;
        this.assignable = builder.assignable;
        this.askable = builder.askable;
    }

    public static class Builder {
        private String code;
        private String label;
        private String description;
        private boolean assignable;
        private boolean askable;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder assignable(boolean assignable) {
            this.assignable = assignable;
            return this;
        }

        public Builder askable(boolean askable) {
            this.askable = askable;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}

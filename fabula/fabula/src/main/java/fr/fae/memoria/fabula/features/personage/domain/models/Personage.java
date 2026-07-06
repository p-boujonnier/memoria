package fr.fae.memoria.fabula.features.personage.domain.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "personages")
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String firstName;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private UUID ownerId;

    public Personage() {
    }

    public static Builder builder() {
        return new Builder();
    }

    private Personage(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.ownerId = builder.ownerId;

    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Integer age;
        private UUID ownerId;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder ownerId(UUID ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Personage build() {
            return new Personage(this);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}

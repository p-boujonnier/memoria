package fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "condition", schema = "hepha")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "index", unique = true)
    private String index;

    private String nameEn;
    private String nameFr;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Array(length = 20)
    @Column(name = "desc_en", columnDefinition = "text[]")
    private String[] descEn;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Array(length = 20)
    @Column(name = "desc_fr", columnDefinition = "text[]")
    private String[] descFr;

    @Column(nullable = false)
    private boolean srdSource = false;

    @Column(nullable = false)
    private boolean locked = false;

    private OffsetDateTime updatedAt;

    public Condition() {}

    public static Builder builder() {
        return new Builder();
    }

    private Condition(Builder builder) {
        this.id = builder.id;
        this.index = builder.index;
        this.nameEn = builder.nameEn;
        this.nameFr = builder.nameFr;
        this.descEn = builder.descEn;
        this.descFr = builder.descFr;
        this.srdSource = builder.srdSource;
        this.locked = builder.locked;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private UUID id;
        private String index;
        private String nameEn;
        private String nameFr;
        private String[] descEn;
        private String[] descFr;
        private boolean srdSource;
        private boolean locked;
        private OffsetDateTime updatedAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder index(String index) {
            this.index = index;
            return this;
        }

        public Builder nameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public Builder nameFr(String nameFr) {
            this.nameFr = nameFr;
            return this;
        }

        public Builder descEn(String[] descEn) {
            this.descEn = descEn;
            return this;
        }

        public Builder descFr(String[] descFr) {
            this.descFr = descFr;
            return this;
        }

        public Builder srdSource(boolean srdSource) {
            this.srdSource = srdSource;
            return this;
        }

        public Builder locked(boolean locked) {
            this.locked = locked;
            return this;
        }

        public Builder updatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Condition build() {
            return new Condition(this);
        }
    }

    @PrePersist
    protected void onCreate() {
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

    public String getNameEn() {
        return nameEn;
    }
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFr() {
        return nameFr;
    }
    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String[] getDescEn() {
        return descEn;
    }
    public void setDescEn(String[] descEn) {
        this.descEn = descEn;
    }

    public String[] getDescFr() {
        return descFr;
    }
    public void setDescFr(String[] descFr) {
        this.descFr = descFr;
    }

    public boolean isSrdSource() {
        return srdSource;
    }
    public void setSrdSource(boolean srdSource) {
        this.srdSource = srdSource;
    }

    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
package model;

import java.util.Objects;

public final class HeroName {

    private final String value;

    public HeroName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {

        if (value == null) {
            throw new IllegalArgumentException(
                    "Le nom du héros ne peut pas être null."
            );
        }

        if (value.length() < 2 || value.length() > 30) {
            throw new IllegalArgumentException(
                    "Le nom doit contenir entre 2 et 30 caractères."
            );
        }

        if (!value.equals(value.trim())) {
            throw new IllegalArgumentException(
                    "Le nom ne peut pas commencer ou terminer par un espace."
            );
        }

        if (!value.matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException(
                    "Le nom doit contenir uniquement des lettres et des espaces."
            );
        }
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HeroName other)) {
            return false;
        }

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
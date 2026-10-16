package model;

import java.util.Objects;

public final class HeroName {

    private final String value;

    public HeroName(String value) {
        validate(value);
        this.value = value;
    }

    //cet code a encore des problemes. selon Claude les problemes sont ceci:

    /*
    * The length bounds are duplicated. 2 and 30 appear in the condition and again inside the message string. If the client changes the max to 25, you have to remember to edit both places, or the error message will lie. A magic number is a literal with no obvious meaning, and the fix is to replace it with a constant whose human-readable name explains what the number means. That constant also serves as live documentation and removes duplicate uses of a number or string. Your messages should be built from the constants, not typed beside them.

    The regex is recompiled on every call. String#matches delegates to Pattern.matches, which calls compile(regex) each time. Sonar flags String.matches called with a literal or constant and recommends refactoring to a Pattern stored in a static final field. Performance is negligible for one name. The real gain is that the rule gets a name, which is the “name the rule instead of commenting it” point in your Séance 2 notes.

    value.equals(value.trim()) says more than the rule does. The spec says “ne commence pas et ne se termine pas par un espace.” trim() strips every character ≤ U+0020: tabs, newlines, control characters. strip() goes further and uses the Unicode definition of whitespace. Your regex already rejects tabs, so behaviour is correct today. But the line reads as “no surrounding whitespace” when the rule is “no surrounding space.” Think about which String methods would state the rule literally.


    Not a cleanliness issue, but you need to know

    "Jean Pierre" (two spaces) passes all four checks. The spec doesn’t say whether that’s allowed. That is a client question, not something you or I decide.
    Uniqueness across the system is also a name rule, and it is absent here. That’s correct. Before you continue, you should be able to say why it can’t live in HeroName.

    What I’m not recommending: splitting each if into its own private method. With four short guard clauses, one constructor (or one validate method) reads fine. Five one-line methods would be noise.DF
    * */

    private void validate(String value) {

        if (value == null) {
            throw new HeroNameInvalidException(
                    "Le nom du héros ne peut pas être null."
            );
        }

        if (value.length() < 2 || value.length() > 30) {
            throw new HeroNameInvalidException(
                    "Le nom doit contenir entre 2 et 30 caractères."
            );
        }

        if (!value.equals(value.trim())) {
            throw new HeroNameInvalidException(
                    "Le nom ne peut pas commencer ou terminer par un espace."
            );
        }

        if (!value.matches("[\\p{L} ]+")) {
            throw new HeroNameInvalidException(
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
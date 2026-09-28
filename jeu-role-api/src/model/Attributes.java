package model;

public final class Attributes {

    private static final int MIN_INITIAL_VALUE = 3;
    private static final int MAX_INITIAL_VALUE = 18;
    private static final int MAX_VALUE = 20;

    private final int force;
    private final int dexterite;
    private final int constitution;
    private final int intelligence;
    private final int sagesse;
    private final int charisme;

    private Attributes(
            int force,
            int dexterite,
            int constitution,
            int intelligence,
            int sagesse,
            int charisme
    ) {
        this.force = force;
        this.dexterite = dexterite;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.sagesse = sagesse;
        this.charisme = charisme;
    }

    public static Attributes create(
            int force,
            int dexterite,
            int constitution,
            int intelligence,
            int sagesse,
            int charisme,
            Race race
    ) {

        validateInitialValue(force, "Force");
        validateInitialValue(dexterite, "Dextérité");
        validateInitialValue(constitution, "Constitution");
        validateInitialValue(intelligence, "Intelligence");
        validateInitialValue(sagesse, "Sagesse");
        validateInitialValue(charisme, "Charisme");

        if (race == null) {
            throw new IllegalArgumentException(
                    "L'espèce est obligatoire."
            );
        }

        return new Attributes(
                applyBonus(force, race.forceBonus()),
                applyBonus(dexterite, race.dexteriteBonus()),
                applyBonus(constitution, race.constitutionBonus()),
                applyBonus(intelligence, race.intelligenceBonus()),
                applyBonus(sagesse, race.sagesseBonus()),
                applyBonus(charisme, race.charismeBonus())
        );
    }

    private static void validateInitialValue(
            int value,
            String attributeName
    ) {
        if (value < MIN_INITIAL_VALUE ||
                value > MAX_INITIAL_VALUE) {

            throw new IllegalArgumentException(
                    attributeName
                            + " doit être comprise entre "
                            + MIN_INITIAL_VALUE
                            + " et "
                            + MAX_INITIAL_VALUE
                            + " avant les bonus d'espèce."
            );
        }
    }

    private static int applyBonus(int value, int bonus) {

        int result = value + bonus;

        return Math.min(result, MAX_VALUE);
    }

    public int force() {
        return force;
    }

    public int dexterite() {
        return dexterite;
    }

    public int constitution() {
        return constitution;
    }

    public int intelligence() {
        return intelligence;
    }

    public int sagesse() {
        return sagesse;
    }

    public int charisme() {
        return charisme;
    }

    public int forceModifier() {
        return modifier(force);
    }

    public int dexteriteModifier() {
        return modifier(dexterite);
    }

    public int constitutionModifier() {
        return modifier(constitution);
    }

    public int intelligenceModifier() {
        return modifier(intelligence);
    }

    public int sagesseModifier() {
        return modifier(sagesse);
    }

    public int charismeModifier() {
        return modifier(charisme);
    }

    private int modifier(int value) {

        /*
         * (valeur - 10) / 2
         * arrondi vers le bas.
         *
         * floorDiv est important pour les valeurs négatives.
         */
        return Math.floorDiv(value - 10, 2);
    }
}
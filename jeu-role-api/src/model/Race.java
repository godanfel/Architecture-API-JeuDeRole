package model;

public enum Race {

    HUMAIN(1, 1, 1, 1, 1, 1),

    ELFE(0, 2, 0, 1, 0, 0),

    NAIN(1, 0, 2, 0, 0, 0),

    ORC(2, 0, 1, -1, 0, 0);

    private final int forceBonus;
    private final int dexteriteBonus;
    private final int constitutionBonus;
    private final int intelligenceBonus;
    private final int sagesseBonus;
    private final int charismeBonus;

    Race(
            int forceBonus,
            int dexteriteBonus,
            int constitutionBonus,
            int intelligenceBonus,
            int sagesseBonus,
            int charismeBonus
    ) {
        this.forceBonus = forceBonus;
        this.dexteriteBonus = dexteriteBonus;
        this.constitutionBonus = constitutionBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.sagesseBonus = sagesseBonus;
        this.charismeBonus = charismeBonus;
    }

    public int forceBonus() {
        return forceBonus;
    }

    public int dexteriteBonus() {
        return dexteriteBonus;
    }

    public int constitutionBonus() {
        return constitutionBonus;
    }

    public int intelligenceBonus() {
        return intelligenceBonus;
    }

    public int sagesseBonus() {
        return sagesseBonus;
    }

    public int charismeBonus() {
        return charismeBonus;
    }
}
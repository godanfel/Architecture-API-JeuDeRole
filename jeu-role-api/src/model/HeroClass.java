package model;

public enum HeroClass {

    GUERRIER(12, 0),

    RODEUR(10, 0),

    MAGE(6, 10),

    CLERC(10, 8);

    private final int baseHealth;
    private final int baseMagic;

    HeroClass(int baseHealth, int baseMagic) {
        this.baseHealth = baseHealth;
        this.baseMagic = baseMagic;
    }

    public int baseHealth() {
        return baseHealth;
    }

    public int baseMagic() {
        return baseMagic;
    }
}
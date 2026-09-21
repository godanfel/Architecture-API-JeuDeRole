package model;

public enum ArmourCategory implements Item{
    SANS_ARMURE(10, true),
    ARMURE_DE_CUIR(11, true),
    ARMURE_DE_MAILLES(16, false);

    private final int armourClass;
    private final boolean dexterityBonus;

    ArmourCategory(int armourClass, boolean dexterityBonus) {
        this.armourClass = armourClass;
        this.dexterityBonus = dexterityBonus;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public boolean hasDexterityBonus() {
        return dexterityBonus;
    }

    @Override
    public void use(Hero hero) {
        hero.equipArmour(this);
    }

    @Override
    public boolean isConsumedOnUse() {
        return false;
    }
}

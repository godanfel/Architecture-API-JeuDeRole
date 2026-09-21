package model;

import java.util.Set;

public enum HeroClassCategory implements HeroClass {

    GUERRIER("Guerrier",12, 0,WeaponCategory.EPEE_LONGUE,ArmourCategory.ARMURE_DE_MAILLES,Set.of(WeaponCategory.EPEE_LONGUE, WeaponCategory.HACHE, WeaponCategory.MASSE,WeaponCategory.DAGUE, WeaponCategory.BATON), Set.of(ArmourCategory.SANS_ARMURE, ArmourCategory.ARMURE_DE_CUIR, ArmourCategory.ARMURE_DE_MAILLES)),
    RODEUR("Rôdeur", 10, 0, WeaponCategory.ARC, ArmourCategory.ARMURE_DE_CUIR, Set.of(WeaponCategory.ARC,WeaponCategory.DAGUE, WeaponCategory.EPEE_LONGUE), Set.of(ArmourCategory.SANS_ARMURE,ArmourCategory.ARMURE_DE_CUIR)),
    MAGE("Mage",  6, 10, WeaponCategory.BATON, ArmourCategory.SANS_ARMURE, Set.of(WeaponCategory.BATON, WeaponCategory.DAGUE), Set.of(ArmourCategory.SANS_ARMURE)),
    CLERC("Clerc", 10, 8, WeaponCategory.MASSE, ArmourCategory.ARMURE_DE_MAILLES, Set.of(WeaponCategory.MASSE, WeaponCategory.BATON), Set.of(ArmourCategory.SANS_ARMURE, ArmourCategory.ARMURE_DE_CUIR, ArmourCategory.ARMURE_DE_MAILLES));

    private final String name;
    private final int baseHp;
    private final int baseMagicPoints;
    private final WeaponCategory startingWeapon;
    private final ArmourCategory startingArmour;
    private final Set<WeaponCategory> allowedWeaponCategories;
    private final Set<ArmourCategory> allowedArmourCategories;

    HeroClassCategory(String name, int baseHp, int baseMagicPoints, WeaponCategory startingWeapon, ArmourCategory startingArmour, Set<WeaponCategory> allowedWeaponCategories, Set<ArmourCategory> allowedArmourCategories) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseMagicPoints = baseMagicPoints;
        this.startingWeapon = startingWeapon;
        this.startingArmour = startingArmour;
        this.allowedWeaponCategories = allowedWeaponCategories;
        this.allowedArmourCategories = allowedArmourCategories;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBaseHp() {
        return this.baseHp;
    }

    @Override
    public int getBaseMagic() {
        return this.baseMagicPoints;
    }

    @Override
    public Set<WeaponCategory> getAllowedWeaponCategories() {
        return this.allowedWeaponCategories;
    }

    @Override
    public Set<ArmourCategory> getAllowedArmourCategories() {
        return this.allowedArmourCategories;
    }

    @Override
    public WeaponCategory startingWeapon() {
        return this.startingWeapon;
    }

    @Override
    public ArmourCategory startingArmour() {
        return this.startingArmour;
    }
}

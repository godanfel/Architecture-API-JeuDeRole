package model;

import java.util.Set;

interface HeroClass {
    String getName();

    int getBaseHp();

    int getBaseMagic();

    Set<WeaponCategory> getAllowedWeaponCategories();

    default boolean canUseWeapon(WeaponCategory category) {
        return getAllowedWeaponCategories().contains(category);
    }

    Set<ArmourCategory> getAllowedArmourCategories();

    default boolean canUseArmour(ArmourCategory category) {
        return getAllowedArmourCategories().contains(category);
    }

    WeaponCategory startingWeapon();

    ArmourCategory startingArmour();
}
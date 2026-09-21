package model;

import java.util.Map;
public enum Species {
    HUMAIN(Map.of(HeroCaracteristic.FORCE, 1, HeroCaracteristic.DEXTERITE, 1, HeroCaracteristic.CONSTITUTION, 1, HeroCaracteristic.INTELLIGENCE, 1, HeroCaracteristic.SAGESSE, 1, HeroCaracteristic.CHARISME, 1)),
    ELFE(Map.of(HeroCaracteristic.DEXTERITE, 2, HeroCaracteristic.INTELLIGENCE, 1)),
    ORC(Map.of(HeroCaracteristic.FORCE, 2, HeroCaracteristic.CONSTITUTION, 1, HeroCaracteristic.INTELLIGENCE, -1)),
    NAIN(Map.of(HeroCaracteristic.CONSTITUTION, 2, HeroCaracteristic.FORCE, 1)),
    ;

    private final Map<HeroCaracteristic, Integer> characteristicsBonuses;

    Species(Map<HeroCaracteristic, Integer> hero){
        this.characteristicsBonuses = hero;
    }

    public Map<HeroCaracteristic, Integer> getCharacteristicsBonuses() {
        return characteristicsBonuses;
    }
}

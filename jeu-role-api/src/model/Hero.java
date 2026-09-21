package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hero {
    private HeroName name;
    private Species species;
    private HeroClass heroClass;
    private Map<HeroCaracteristic, AbilityScore> characteristics;
    private ArmourCategory equippedArmour;
    private WeaponCategory equippedWeapon;

    private Set<ArmourCategory> allowedArmourCategories;
    private Set<WeaponCategory> allowedWeaponCategories;


    private int level;
    private int xp;

    private int currentHP;
    private int maxHP;

    private int currentMagicPoints;
    private int maxMagicPoints;

    private Inventory inventory;

    public Hero(HeroName name, Species species, HeroClass heroClass, Map<HeroCaracteristic, Integer> rawCharacteristics){
        this.name = name;
        this.species = species;
        this.heroClass = heroClass;

        this.characteristics = new HashMap<>();
        for (HeroCaracteristic caracteristic : HeroCaracteristic.values()){
            int raw = rawCharacteristics.get(caracteristic);
            int bonus = species.getCharacteristicsBonuses().getOrDefault(caracteristic, 0);
            this.characteristics.put(caracteristic, new AbilityScore(raw + bonus));
        }

        this.level = 1;
        this.xp = 0;

        //ceci assure que le MaxHp deviens pas 0, en fait, Math.max prends le maximum des deux valeurs, si jamais le hero finis par avoir un HP de -1
        //(on sait jamais) on peut le corriger ici. En deuxieme on sait que si le MaxHp est >1 il serait choisi comme valeur finale
        this.maxHP = Math.max(1, heroClass.getBaseHp() + (characteristics.get(HeroCaracteristic.CONSTITUTION)).getModifier());
        this.maxMagicPoints = heroClass.getBaseMagic();

        this.currentMagicPoints = maxMagicPoints;
        this.currentHP = maxHP;

        this.equippedArmour = heroClass.startingArmour();
        this.equippedWeapon = heroClass.startingWeapon();

        this.inventory = new Inventory();

        this.inventory.addItem(this.equippedArmour);
        this.inventory.addItem(this.equippedWeapon);
    }

    public HeroName getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    //on retourne unmodifiableMap pour eviter que n'importe qui peut modifier le Hashmap a force avec
    //.put(HeroCaracteristic.FORCE, uneAutreValeur)

    public Map<HeroCaracteristic, AbilityScore> getCharacteristics() {
        return Collections.unmodifiableMap(characteristics);
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentMagicPoints() {
        return currentMagicPoints;
    }

    public int getMaxMagicPoints() {
        return maxMagicPoints;
    }

    public ArmourCategory getEquippedArmour() {
        return equippedArmour;
    }

    public WeaponCategory getEquippedWeapon() {
        return equippedWeapon;
    }

    public void equipWeapon(WeaponCategory weapon){
        if(!heroClass.canUseWeapon(weapon)){
            throw new ItemInvalidException("Invalid weapon choice, hero can't equip this weapon");
        }
        if(!inventory.contains(weapon)){
            throw new InventoryInvalidException("Invalid weapon choice, Hero does not own this weapon");
        }
        equippedWeapon = weapon;

    }

    public void equipArmour(ArmourCategory armour){
        if(!heroClass.canUseArmour(armour)) {
            throw new ItemInvalidException("Invalid Armour choice, hero can't equip this Armour");
        }
        if(!inventory.contains(armour)){
            throw new InventoryInvalidException("Invalid Armour choice, Hero does not own this armour");
        }
        equippedArmour = armour;

    }

    public Set<ArmourCategory> getAllowedArmourCategories() {
        return Set.copyOf(heroClass.getAllowedArmourCategories());
    }

    public Set<WeaponCategory> getAllowedWeaponCategories() {
        return Set.copyOf(heroClass.getAllowedWeaponCategories());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void heal(int amount){
        this.currentHP = Math.min(currentHP + amount, maxHP);
    }

}
